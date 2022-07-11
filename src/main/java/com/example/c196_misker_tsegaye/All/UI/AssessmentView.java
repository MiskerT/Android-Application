package com.example.c196_misker_tsegaye.All.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.c196_misker_tsegaye.All.Database.Repository;
import com.example.c196_misker_tsegaye.All.Entities.AssessmentEntity;
import com.example.c196_misker_tsegaye.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class AssessmentView extends AppCompatActivity {

    String assessmentID;
    TextView assessmentNameView;
    TextView assessmentDateView;
    TextView assessmentTypeView;
    Repository repository;
    AssessmentEntity assessment;
    int assessmentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        repository = new Repository(getApplication());

        assessmentID = getIntent().getStringExtra("assessmentID");
        assessment = repository.getAssessmentByID(Integer.valueOf(assessmentID)).get(0);

        assessmentNameView = findViewById(R.id.assessmentNameView);
        String assessmentName = assessment.getAssessmentName();
        assessmentNameView.setText(assessmentName);
        assessmentDateView = findViewById(R.id.assessmentDateView);
        String assessmentDate = assessment.getDueDate();
        assessmentDateView.setText(assessmentDate);
        assessmentTypeView = findViewById(R.id.assessmentTypeView);
        String assessmentType = assessment.getType();
        assessmentTypeView.setText(assessmentType);

        //set activity title
        setTitle(assessment.getAssessmentName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;

            case R.id.notes:
                Intent intent2 = new Intent(this, Notes.class);
                intent2.putExtra("assessmentID", assessmentID);
                startActivity(intent2);
                return true;

            case R.id.delete:
                repository.delete(assessment);
                this.finish();
                return true;

            case R.id.notify:
                String dueDate = assessment.getDueDate();
                String format = "mm/dd/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
                Date date = null;
                try{
                    date = sdf.parse(dueDate);
                }catch(ParseException e){
                    e.printStackTrace();
                }
                Long trigger = date.getTime();
                Intent intent = new Intent(AssessmentView.this, MyReceiver.class);
                String course = repository.getCourseByID(assessment.getCourseID()).get(0).getCourseName();
                intent.putExtra("key", "You have an assessment coming up soon: " + assessment.getAssessmentName() + " for Course: " + course);
                PendingIntent sender = PendingIntent.getBroadcast(AssessmentView.this, ++MainActivity.numAlert, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_note, menu);
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        getMenuInflater().inflate(R.menu.menu_notify, menu);
        return true;
    }

    public void editAssessment(View view) {
        Intent intent = new Intent(this, EditAssessments.class );
        intent.putExtra("assessmentID", assessmentID);
        intent.putExtra("courseID", assessment.getCourseID()+"");
        startActivity(intent);
    }
}