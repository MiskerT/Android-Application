package com.example.c196_misker_tsegaye.All.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Course_Table", foreignKeys = {@ForeignKey(entity = TermEntity.class,
        parentColumns = "termID",
        childColumns = "termID",
        onDelete = ForeignKey.CASCADE)
})
public class CourseEntity {
    @PrimaryKey(autoGenerate = true)
    private int courseID;
    @ColumnInfo(index = true)
    private int termID;
    private String courseName;
    private String startDate;
    private String endDate;
    private String status;
    private String instName;
    private String email;
    private String phone;

    public CourseEntity(String courseName) {
        this.courseID = courseID;
        this.courseName = courseName;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String eMail) {
        this.email = eMail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "courseID=" + courseID +
                ", courseName='" + courseName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", status='" + status + '\'' +
                ", instName='" + instName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
