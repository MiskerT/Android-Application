package com.example.c196_misker_tsegaye.All.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196_misker_tsegaye.All.Entities.AssessmentEntity;
import com.example.c196_misker_tsegaye.All.Entities.CourseEntity;
import com.example.c196_misker_tsegaye.All.Entities.NoteEntity;
import com.example.c196_misker_tsegaye.All.Entities.TermEntity;

import java.util.List;

@Dao
public interface AppDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TermEntity term);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CourseEntity course);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AssessmentEntity assessment);
    @Insert
    void insert(NoteEntity note);


    @Update
    void update(TermEntity term);
    @Update
    void update(CourseEntity course);
    @Update
    void update(AssessmentEntity assessment);
    @Update
    void update(NoteEntity note);


    @Delete
    void delete(TermEntity term);
    @Delete
    void delete(CourseEntity term);
    @Delete
    void delete(AssessmentEntity assessment);
    @Delete
    void delete(NoteEntity note);


    @Query("SELECT * FROM Term_Table ORDER BY termID ")
    List<TermEntity> getAllTerms();
    @Query("SELECT * FROM Term_Table WHERE termID = :term ")
    List<TermEntity> getTermByID(int term);
    @Query("SELECT * FROM Course_Table ORDER BY courseID ")
    List<CourseEntity> getAllCourses();
    @Query("SELECT * FROM Course_Table  WHERE termID = :term ORDER BY courseID")
    List<CourseEntity> getAllCoursesTerm(int term);
    @Query("SELECT * FROM Course_Table  WHERE courseID = :course")
    List<CourseEntity> getCourseByID(int course);
    @Query("SELECT * FROM A_Table ORDER BY assessmentID")
    List<AssessmentEntity> getAllAssessments();
    @Query("SELECT * FROM A_Table  WHERE courseID = :course ORDER BY assessmentID")
    List<AssessmentEntity> getAllAssessmentsCourse(int course);
    @Query("SELECT * FROM A_Table  WHERE assessmentID = :assessment")
    List<AssessmentEntity> getAssessmentByID(int assessment);
    @Query("SELECT * FROM Note_Table ORDER BY noteID")
    List<NoteEntity> getAllNotes();
    @Query("SELECT * FROM Note_table  WHERE assessmentID = :assessment ORDER BY noteID")
    List<NoteEntity> getAllNotesAssessment(int assessment);
    @Query("SELECT * FROM Note_table  WHERE noteID = :note")
    List<NoteEntity> getNoteByID(int note);


}
