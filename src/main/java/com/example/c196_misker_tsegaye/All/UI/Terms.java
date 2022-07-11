package com.example.c196_misker_tsegaye.All.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.c196_misker_tsegaye.All.Database.Repository;
import com.example.c196_misker_tsegaye.All.Entities.TermEntity;
import com.example.c196_misker_tsegaye.R;

import java.util.ArrayList;
import java.util.List;

public class Terms extends AppCompatActivity {

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        repository = new Repository(getApplication());
        ArrayList<TermEntity> terms = (ArrayList<TermEntity>) repository.getAllTerms();
//Recycler view to show terms
        RecyclerView recyclerView = findViewById(R.id.termRecyclerView);
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(terms);
    }

    @Override
    public void onResume(){
        super.onResume();
        ArrayList<TermEntity> terms = (ArrayList<TermEntity>) repository.getAllTerms();
        RecyclerView recyclerView = findViewById(R.id.termRecyclerView);
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(terms);
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
        Intent intent = new Intent(Terms.this, AddTerms.class);
        startActivity(intent);
    }
}