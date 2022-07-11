package com.example.c196_misker_tsegaye.All.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Note_Table", foreignKeys = {@ForeignKey(entity = AssessmentEntity.class,
        parentColumns = "assessmentID",
        childColumns = "assessmentID",
        onDelete = ForeignKey.CASCADE)
})

public class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    private int noteID;
    @ColumnInfo(index = true)
    private int assessmentID;

    private String noteName;
    private String noteText;


    public NoteEntity(String noteName) {
        this.noteName = noteName;
    }

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    @Override
    public String toString() {
        return "NoteEntity{" +
                "noteID=" + noteID +
                ", assessmentID=" + assessmentID +
                ", noteName='" + noteName + '\'' +
                ", noteText='" + noteText + '\'' +
                '}';
    }

}
