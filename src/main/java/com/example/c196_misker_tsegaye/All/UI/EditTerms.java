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

public class EditTerms extends AppCompatActivity {

    String termName;
    EditText editName;
    String startDate;
    EditText editStartDate;
    String endDate;
    EditText editEndDate;
    Repository repository;
    TermEntity term;
    int termID;
    Checker checker;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_terms);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        repository = new Repository(getApplication());
        termID = Integer.parseInt(getIntent().getStringExtra("termID"));

        term = repository.getAllTerms().get(termID - 1);

        editName = findViewById(R.id.editTextTextPersonName);
        editName.setText(term.getTermName());
        editStartDate = findViewById(R.id.editTextDate3);
        editStartDate.setText(term.getStartDate());
        editEndDate = findViewById(R.id.editTextDate4);
        editEndDate.setText(term.getEndDate());

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

    public void saveTerm(View view) {
        String screenName = editName.getText().toString();
        String screenStart = editStartDate.getText().toString();
        String screenEnd = editEndDate.getText().toString();
        term.setTermName(screenName);
        term.setStartDate(screenStart);
        term.setEndDate(screenEnd);
        checker = new Checker(term);
        if(checker.enter){
            repository.update(term);
            Intent intent = new Intent(this, TermView.class);
            intent.putExtra("editTextTextPersonName", term.getTermName());
            intent.putExtra("editTextDate3", term.getStartDate());
            intent.putExtra("editTextDate4", term.getEndDate());
            intent.putExtra("termID" , term.getTermID()+"");
            startActivity(intent);
        } else{
            Toast toast = Toast.makeText(this, "Please make sure you all the fields are correctly filled and formatted", Toast.LENGTH_LONG);
            toast.show();
        }


    }
}