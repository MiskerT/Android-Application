package com.example.c196_misker_tsegaye.All.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.c196_misker_tsegaye.All.Database.Repository;
import com.example.c196_misker_tsegaye.All.Entities.AssessmentEntity;
import com.example.c196_misker_tsegaye.R;

public class EditAssessments extends AppCompatActivity {

    EditText assessmentName;
    EditText assessmentDate;
    RadioGroup typeGroup;
    RadioButton typeButton;
    AssessmentEntity assessment;
    String courseID;
    int intCourse;
    String assessmentID;
    String type;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assessments);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        assessmentID = getIntent().getStringExtra("assessmentID");
        courseID = getIntent().getStringExtra("courseID");
        intCourse = Integer.valueOf(courseID);
        repository = new Repository(getApplication());

        setTitle("Add Assessment");

        assessmentName = findViewById(R.id.editAssessmentName);
        assessmentDate = findViewById(R.id.editAssessmentDate);
        typeGroup = (RadioGroup) findViewById(R.id.typeRadio);

        if(assessmentID != null){
            assessment = repository.getAssessmentByID(Integer.valueOf(assessmentID)).get(0);
            assessmentName.setText(assessment.getAssessmentName());
            assessmentDate.setText(assessment.getDueDate());
            String choice = assessment.getType().toString();
            switch(choice){
                case "Performance":
                    typeButton = (RadioButton) findViewById(R.id.radioButton6);
                    typeButton.setChecked(true);
                    break;
                case "Objective":
                    typeButton = (RadioButton) findViewById(R.id.radioButton5);
                    typeButton.setChecked(true);
                    break;
            }
            setTitle("Edit Assessment");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void saveAssessment(View view) {
        String name = assessmentName.getText().toString();
        String date = assessmentDate.getText().toString();
        int radioSelected = typeGroup.getCheckedRadioButtonId();
        View radioButtonID = typeGroup.findViewById(radioSelected);
        int index = typeGroup.indexOfChild(radioButtonID);
        typeButton =(RadioButton) typeGroup.getChildAt(index);
        if(typeButton != null){
            type = typeButton.getText().toString();

            if(assessmentID == null){
                AssessmentEntity newAssessment = new AssessmentEntity(name);
                newAssessment.setDueDate(date);
                newAssessment.setType(type);
                newAssessment.setCourseID(intCourse);
                Checker checker = new Checker(newAssessment);
                if(checker.enter){
                    repository.insert(newAssessment);
                    finish();
                }else{
                    Toast toast = Toast.makeText(this, "Please make sure you all the fields are correctly filled and formatted", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
            else{
                AssessmentEntity oldAssessment = repository.getAssessmentByID(Integer.valueOf(assessmentID)).get(0);
                oldAssessment.setAssessmentName(name);
                oldAssessment.setDueDate(date);
                oldAssessment.setType(type);
                Checker checker = new Checker(oldAssessment);
                if(checker.enter){
                    repository.update(oldAssessment);
                    finish();
                }else{
                    Toast toast = Toast.makeText(this, "Please make sure you all the fields are correctly filled and formatted", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        }
        else{
            Toast toast = Toast.makeText(this, "Please select the type of assessment", Toast.LENGTH_LONG);
            toast.show();
        }


    }
}