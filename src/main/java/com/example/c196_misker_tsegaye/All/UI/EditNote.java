package com.example.c196_misker_tsegaye.All.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c196_misker_tsegaye.All.Database.Repository;
import com.example.c196_misker_tsegaye.All.Entities.AssessmentEntity;
import com.example.c196_misker_tsegaye.All.Entities.NoteEntity;
import com.example.c196_misker_tsegaye.R;

public class EditNote extends AppCompatActivity {

    EditText noteName;
    EditText noteText;
    String noteID;
    String assessmentID;
    int assessmentInt;
    NoteEntity note;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        noteID = getIntent().getStringExtra("noteID");
        assessmentID = getIntent().getStringExtra("assessmentID");
        assessmentInt = Integer.valueOf(assessmentID);
        setTitle("Add Note");

        repository = new Repository(getApplication());

        noteName = findViewById(R.id.editNoteName);
        noteText = findViewById(R.id.editNoteText);

        if(noteID != null) {
            note = repository.getNoteByID(Integer.valueOf(noteID)).get(0);
            noteName.setText(note.getNoteName());
            noteText.setText(note.getNoteText());
            setTitle("Edit Note");
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

    public void saveNote(View view) {
        String name = noteName.getText().toString();
        String text = noteText.getText().toString();

        if(noteID == null){
            NoteEntity newNote = new NoteEntity(name);
            newNote.setNoteText(text);
            newNote.setAssessmentID(assessmentInt);
            Checker checker = new Checker(newNote);
            if(checker.enter){
                repository.insert(newNote);
                finish();
            }else{
                Toast toast = Toast.makeText(this, "Please make sure you all the fields are correctly filled and formatted", Toast.LENGTH_LONG);
                toast.show();
            }
        }
        else{
            //NoteEntity oldNote = repository.getAllNotes().get(Integer.valueOf(noteID) - 1);
            NoteEntity oldNote = repository.getNoteByID(Integer.valueOf(noteID)).get(0);
            oldNote.setNoteName(name);
            oldNote.setNoteText(text);
            Checker checker = new Checker(oldNote);
            if(checker.enter){
                repository.update(oldNote);
                finish();
            }else{
                Toast toast = Toast.makeText(this, "Please make sure you all the fields are correctly filled and formatted", Toast.LENGTH_LONG);
                toast.show();
            }
        }


    }
}