package com.example.c196_misker_tsegaye.All.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "A_Table", foreignKeys = {@ForeignKey(entity = CourseEntity.class,
        parentColumns = "courseID",
        childColumns = "courseID",
        onDelete = ForeignKey.CASCADE)
})
public class AssessmentEntity {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    @ColumnInfo(index = true)
    private int courseID;
    private String assessmentName;
    private String type;
    private String dueDate;

    public AssessmentEntity(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "AssessmentEntity{" +
                "assessmentID=" + assessmentID +
                ", courseID=" + courseID +
                ", assessmentName='" + assessmentName + '\'' +
                ", type='" + type + '\'' +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}
