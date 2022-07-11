package com.example.c196_misker_tsegaye.All.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.c196_misker_tsegaye.All.Database.Repository;
import com.example.c196_misker_tsegaye.All.Entities.CourseEntity;
import com.example.c196_misker_tsegaye.All.Entities.NoteEntity;
import com.example.c196_misker_tsegaye.R;

import java.nio.channels.InterruptedByTimeoutException;

public class NoteView extends AppCompatActivity {

    TextView noteView;
    String noteID;
    Repository repository;
    NoteEntity note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        repository = new Repository(getApplication());

        noteID = getIntent().getStringExtra("noteID");
        note = repository.getNoteByID(Integer.valueOf(noteID)).get(0);

        noteView = findViewById(R.id.textView17);
        noteView.setText(note.getNoteText());

        setTitle(note.getNoteName());


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.delete:
                repository.delete(note);
                this.finish();
                return true;
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, note.getNoteText());
                sendIntent.putExtra(Intent.EXTRA_TITLE, note.getNoteName());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return true;
    }

    public void editNote(View view) {
        Intent intent = new Intent(this, EditNote.class);
        intent.putExtra("noteID", noteID);
        intent.putExtra("assessmentID", note.getAssessmentID()+"");
        startActivity(intent);
    }
}