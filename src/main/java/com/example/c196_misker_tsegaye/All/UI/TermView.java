package com.example.c196_misker_tsegaye.All.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c196_misker_tsegaye.All.Database.Repository;
import com.example.c196_misker_tsegaye.All.Entities.CourseEntity;
import com.example.c196_misker_tsegaye.All.Entities.TermEntity;
import com.example.c196_misker_tsegaye.R;

import java.util.List;

public class TermView extends AppCompatActivity {
    String termName;
    TextView termNameView;
    String startDate;
    TextView termDates;
    String endDate;
    Repository repository;
    String termID;
    RecyclerView recyclerView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        termID = getIntent().getStringExtra("termID");
        termName = getIntent().getStringExtra("editTextTextPersonName");
        termNameView = findViewById(R.id.textViewTerm);
        termNameView.setText(termName);
        startDate = getIntent().getStringExtra("editTextDate3");
        endDate = getIntent().getStringExtra("editTextDate4");
        termDates = findViewById(R.id.textViewDates);
        termDates.setText(startDate + "  -  " + endDate);

        //set activity title
        setTitle(termName);

        repository = new Repository(getApplication());
        recyclerView = findViewById(R.id.courseRecyclerView1);
        List<CourseEntity> courses = repository.getAllCourses();
        int termInt = Integer.valueOf(termID);
        List<CourseEntity> coursesTerm = repository.getAllCoursesTerm(termInt);

        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter.setCourses(coursesTerm);
    }
    @Override
    public void onResume(){
        super.onResume();

        recyclerView = findViewById(R.id.courseRecyclerView1);
        List<CourseEntity> courses = repository.getAllCourses();
        int termInt = Integer.valueOf(termID);
        List<CourseEntity> coursesTerm = repository.getAllCoursesTerm(termInt);


        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter.setCourses(coursesTerm);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.delete:
                List<CourseEntity> check = repository.getAllCoursesTerm(Integer.valueOf(termID));
                if(check.size() == 0){
                    TermEntity term = repository.getTermByID(Integer.valueOf(termID)).get(0);
                    repository.delete(term);
                    this.finish();
                }else{
                    Toast toast = Toast.makeText(this, "You can't delete a term that contains courses.", Toast.LENGTH_LONG);
                    toast.show();
                }

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }
    public void editTerm(View view) {
        Intent intent = new Intent(TermView.this, EditTerms.class);
        intent.putExtra("termID" , termID);
        startActivity(intent);
    }

    public void addCourse(View view) {
        Intent intent = new Intent(this, EditCourses.class);
        intent.putExtra("termID" , termID);
        startActivity(intent);


    }
}