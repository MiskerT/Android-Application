package com.example.c196_misker_tsegaye.All.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.c196_misker_tsegaye.All.Entities.CourseEntity;
import com.example.c196_misker_tsegaye.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CourseView extends AppCompatActivity {
    String courseName;
    TextView courseNameView;
    String startDate;
    String endDate;
    String name;
    String phone;
    String email;
    String status;
    int courseIndex;
    TextView courseDateView;
    String courseID;
    CourseEntity course;
    RecyclerView recyclerView;
    Repository repository;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        courseName = getIntent().getStringExtra("editCourseName");
        startDate = getIntent().getStringExtra("courseStartDate");
        endDate = getIntent().getStringExtra("courseEndDate");
        courseNameView = findViewById(R.id.courseName2);
        courseDateView = findViewById(R.id.courseDates);
        courseNameView.setText(courseName);
        courseDateView.setText(startDate + "  -  " + endDate );

        courseID = getIntent().getStringExtra("courseID");
        repository = new Repository(getApplication());
        if(courseID != null)
        {
            course = repository.getCourseByID(Integer.valueOf(courseID)).get(0);
            name = course.getInstName();
            phone = course.getPhone();
            email = course.getEmail();
            status = course.getStatus();
        }
        //set activity title
        setTitle(courseName);
// Assessment recycler View

        recyclerView = findViewById(R.id.AssessmentRecyclerView);
        int courseNum = Integer.valueOf(courseID);
        List<AssessmentEntity> assessments = repository.getAllAssessmentsCourse(courseNum);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessments(assessments);
    }
    @Override
    public void onResume(){
        super.onResume();

        recyclerView = findViewById(R.id.AssessmentRecyclerView);
        int courseNum = Integer.valueOf(courseID);
        List<AssessmentEntity> assessments = repository.getAllAssessmentsCourse(courseNum);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessments(assessments);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.delete:
                repository.delete(course);
                this.finish();
                return true;

            case R.id.notify:
                String stDate = course.getStartDate();
                String enDate = course.getEndDate();
                String format = "mm/dd/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
                Date date1 = null;
                Date date2 = null;
                try{
                    date1 = sdf.parse(stDate);
                    date2 = sdf.parse(enDate);
                }catch(ParseException e){
                    e.printStackTrace();
                }
                Long trigger = date1.getTime();
                Long trigger2 = date2.getTime();
                Intent intent = new Intent(CourseView.this, MyReceiver.class);
                String term = repository.getTermByID(course.getTermID()).get(0).getTermName();
                intent.putExtra("key", "You have a course starting soon: " + course.getCourseName() + " for Term: " + term);
                PendingIntent sender = PendingIntent.getBroadcast(CourseView.this, ++MainActivity.numAlert, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);

                Intent intent2 = new Intent(CourseView.this, MyReceiver.class);
                intent2.putExtra("key", "You have a course ending soon: " + course.getCourseName() + " for Term: " + term);
                PendingIntent sender2 = PendingIntent.getBroadcast(CourseView.this, ++MainActivity.numAlert, intent2, 0);
                AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager2.set(AlarmManager.RTC_WAKEUP, trigger2, sender2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        getMenuInflater().inflate(R.menu.menu_notify, menu);
        return true;
    }

    public void editCourse(View view) {
        Intent intent = new Intent(this, EditCourses.class);
        intent.putExtra("courseID", courseID);
        intent.putExtra("courseName", courseName);
        intent.putExtra("cStartDate", startDate);
        intent.putExtra("cEndDate", endDate);
        intent.putExtra("name", name);
        intent.putExtra("phone", phone);
        intent.putExtra("email", email);
        intent.putExtra("status", status);
        startActivity(intent);
    }

    public void addAssessment(View view) {
        Intent intent = new Intent(this, EditAssessments.class);
        intent.putExtra("courseID", courseID);
        startActivity(intent);
    }
}