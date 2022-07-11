package com.example.c196_misker_tsegaye.All.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.c196_misker_tsegaye.All.Database.Repository;
import com.example.c196_misker_tsegaye.All.Entities.CourseEntity;
import com.example.c196_misker_tsegaye.R;

public class EditCourses extends AppCompatActivity {
    EditText cName;
    EditText cStartDate;
    EditText cEndDate;
    EditText insName;
    EditText insPhone;
    EditText insEmail;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Repository repository;
    String termID;
    String courseID;

    String courseName;
    String startDate;
    String endDate;
    String name;
    String phone;
    String email;
    String status;
    int term;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_courses);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        termID = getIntent().getStringExtra("termID");
        courseID = getIntent().getStringExtra("courseID");

        cName = findViewById(R.id.editCourseName);
        cName.setText(getIntent().getStringExtra("courseName"));
        cStartDate= findViewById(R.id.editCourseSDate);
        cStartDate.setText(getIntent().getStringExtra("cStartDate"));
        cEndDate = findViewById(R.id.editCourseEDate);
        cEndDate.setText(getIntent().getStringExtra("cEndDate"));
        insName = findViewById(R.id.editInsName);
        insName.setText(getIntent().getStringExtra("name"));
        insPhone = findViewById(R.id.editInsPhone);
        insPhone.setText(getIntent().getStringExtra("phone"));
        insEmail = findViewById(R.id.editInsEmail);
        insEmail.setText(getIntent().getStringExtra("email"));

        setTitle("Add Course");

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        if(courseID != null) {
            String choice = getIntent().getStringExtra("status");
            RadioButton selected;
            switch(choice){
                case "In progress":
                    selected = (RadioButton) findViewById(R.id.radioButton);
                    selected.setChecked(true);
                    break;
                case "Completed":
                    selected = (RadioButton) findViewById(R.id.radioButton2);
                    selected.setChecked(true);
                    break;
                case "Dropped":
                    selected = (RadioButton) findViewById(R.id.radioButton3);
                    selected.setChecked(true);
                    break;
                case "Plan to take":
                    selected = (RadioButton) findViewById(R.id.radioButton4);
                    selected.setChecked(true);
                    break;
                default:
                    break;
            }
            setTitle("Edit Course");
        }



        repository = new Repository(getApplication());



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

    public void saveCourse(View view) {
        courseName = cName.getText().toString();
        startDate = cStartDate.getText().toString();
        endDate = cEndDate.getText().toString();
        name = insName.getText().toString();
        phone = insPhone.getText().toString();
        email = insEmail.getText().toString();
        int radioSelected = radioGroup.getCheckedRadioButtonId();
        View radioButtonID = radioGroup.findViewById(radioSelected);
        int index = radioGroup.indexOfChild(radioButtonID);
        radioButton =(RadioButton) radioGroup.getChildAt(index);
        if(radioButton != null){
            status = radioButton.getText().toString();



            if(courseID == null){
                CourseEntity course = new CourseEntity(courseName);
                course.setStartDate(startDate);
                term = Integer.valueOf(termID);
                course.setTermID(term);
                course.setEndDate(endDate);
                course.setStatus(status);
                course.setInstName(name);
                course.setPhone(phone);
                course.setEmail(email);
                Checker checker = new Checker(course);
                if(checker.enter){
                    repository.insert(course);
                    finish();
                }
                else{
                    Toast toast = Toast.makeText(this, "Please make sure you all the fields are correctly filled and formatted", Toast.LENGTH_LONG);
                    toast.show();
                }

            }else{
                int oldCourseID = Integer.valueOf(courseID);
                //CourseEntity oldCourse = repository.getAllCourses().get(oldCourseID - 1);
                CourseEntity oldCourse = repository.getCourseByID(Integer.valueOf(courseID)).get(0);
                oldCourse.setCourseName(courseName);
                oldCourse.setStartDate(startDate);
                oldCourse.setEndDate(endDate);
                oldCourse.setStatus(status);
                oldCourse.setInstName(name);
                oldCourse.setPhone(phone);
                oldCourse.setEmail(email);
                Checker checker = new Checker(oldCourse);
                if(checker.enter){
                    repository.update(oldCourse);
                    finish();
                }
                else{
                    Toast toast = Toast.makeText(this, "Please make sure you all the fields are correctly filled and formatted", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        }else{
            Toast toast = Toast.makeText(this, "Please select the status of course", Toast.LENGTH_LONG);
            toast.show();
        }

    }
}