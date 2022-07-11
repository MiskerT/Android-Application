package com.example.c196_misker_tsegaye.All.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.c196_misker_tsegaye.All.Database.Repository;
import com.example.c196_misker_tsegaye.All.Entities.TermEntity;
import com.example.c196_misker_tsegaye.R;

public class AddTerms extends AppCompatActivity {
    String termName;
    EditText editName;
    String startDate;
    EditText editStartDate;
    String endDate;
    EditText editEndDate;
    Repository repository;
    int termID;
    Checker checker;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_terms);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        repository = new Repository(getApplication());

        editName = findViewById(R.id.addTermName);
        editStartDate = findViewById(R.id.addStartDate);
        editEndDate = findViewById(R.id.addEndDate);

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


    public void addTerm(View view) {
        termName = editName.getText().toString();
        startDate = editStartDate.getText().toString();
        endDate = editEndDate.getText().toString();
        TermEntity term = new TermEntity(termName);
        term.setStartDate(startDate);
        term.setEndDate(endDate);
        checker = new Checker(term);
        if(checker.enter){
            repository.insert(term);
            Intent intent = new Intent(this, Terms.class);
            startActivity(intent);
        }else{
            Toast toast = Toast.makeText(this, "Please make sure you all the fields are correctly filled and formatted", Toast.LENGTH_LONG);
            toast.show();
        }

    }
}