package com.example.c196_misker_tsegaye.All.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.sql.Timestamp;

@Entity(tableName = "Term_Table")
public class TermEntity {

    @PrimaryKey(autoGenerate = true)
    private int termID;

    private String termName;
    private String startDate;
    private String endDate;


    public TermEntity(String termName){
        this.termName = termName;

    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    @Override
    public String toString() {
        return "TermEntity{" +
                "termID=" + termID +
                ", termName='" + termName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
