package com.example.c196_misker_tsegaye.All.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.c196_misker_tsegaye.All.Database.Repository;
import com.example.c196_misker_tsegaye.All.Entities.AssessmentEntity;
import com.example.c196_misker_tsegaye.All.Entities.NoteEntity;
import com.example.c196_misker_tsegaye.R;

import java.util.List;

public class Notes extends AppCompatActivity {

    RecyclerView recyclerView;
    String assessmentID;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        assessmentID = getIntent().getStringExtra("assessmentID");
        repository = new Repository(getApplication());

    //recycler view for notes
        recyclerView = findViewById(R.id.recyclerViewNotes);
        int assessmentNum = Integer.valueOf(assessmentID);
        List<NoteEntity> notes = repository.getAllNotesAssessment(assessmentNum);
        final NotesAdapter notesAdapter = new NotesAdapter(this);
        recyclerView.setAdapter(notesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesAdapter.setAssessments(notes);
    }
    @Override
    public void onResume(){
        super.onResume();

        recyclerView = findViewById(R.id.recyclerViewNotes);
        int assessmentNum = Integer.valueOf(assessmentID);
        List<NoteEntity> notes = repository.getAllNotesAssessment(assessmentNum);
        final NotesAdapter notesAdapter = new NotesAdapter(this);
        recyclerView.setAdapter(notesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesAdapter.setAssessments(notes);
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

    public void addNote(View view) {
        Intent intent = new Intent(this, EditNote.class);
        intent.putExtra("assessmentID", assessmentID);
        startActivity(intent);
    }
}