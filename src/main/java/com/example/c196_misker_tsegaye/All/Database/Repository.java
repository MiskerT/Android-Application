package com.example.c196_misker_tsegaye.All.Database;

import android.app.Application;

import com.example.c196_misker_tsegaye.All.DAO.AppDao;
import com.example.c196_misker_tsegaye.All.Entities.AssessmentEntity;
import com.example.c196_misker_tsegaye.All.Entities.CourseEntity;
import com.example.c196_misker_tsegaye.All.Entities.NoteEntity;
import com.example.c196_misker_tsegaye.All.Entities.TermEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {

    private AppDao appDao;
    private List<TermEntity> allTerms;
    private List<TermEntity> termByID;
    private List<CourseEntity> allCourses;
    private List<CourseEntity> allCoursesTerm;
    private List<CourseEntity> courseByID;
    private List<AssessmentEntity> allAssessments;
    private List<AssessmentEntity> allAssessmentsCourse;
    private List<AssessmentEntity> assessmentByID;
    private List<NoteEntity> allNotes;
    private List<NoteEntity> allNotesAssessment;
    private List<NoteEntity> noteByID;
    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        appDatabaseBuilder db = appDatabaseBuilder.getDatabase(application);
        appDao = db.appDao();
    }

    public List<TermEntity> getAllTerms(){
        databaseExecutor.execute(()->{
            allTerms = appDao.getAllTerms();
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        return allTerms;
    }
    public List<TermEntity> getTermByID(int term){
        databaseExecutor.execute(()->{
            termByID = appDao.getTermByID(term);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        return termByID;
    }

    public void insert(TermEntity term){
        databaseExecutor.execute(()->{
            appDao.insert(term);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(TermEntity term){
        databaseExecutor.execute(()->{
            appDao.update(term);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(TermEntity term){
        databaseExecutor.execute(()->{
            appDao.delete(term);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
//repository for Course entity

    public List<CourseEntity> getAllCourses(){
        databaseExecutor.execute(()->{
            allCourses = appDao.getAllCourses();
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        return allCourses;
    }

    public List<CourseEntity> getCourseByID(int course){
        databaseExecutor.execute(()->{
            courseByID = appDao.getCourseByID(course);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        return courseByID;
    }

    public List<CourseEntity> getAllCoursesTerm(int term){
        databaseExecutor.execute(()->{
            allCoursesTerm = appDao.getAllCoursesTerm(term);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        return allCoursesTerm;
    }

    public void insert(CourseEntity course){
        databaseExecutor.execute(()->{
            appDao.insert(course);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(CourseEntity course){
        databaseExecutor.execute(()->{
            appDao.update(course);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(CourseEntity course){
        databaseExecutor.execute(()->{
            appDao.delete(course);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

// Repository for assessments


    public List<AssessmentEntity> getAllAssessments(){
        databaseExecutor.execute(()->{
            allAssessments = appDao.getAllAssessments();
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        return allAssessments;
    }

    public List<AssessmentEntity> getAssessmentByID(int assessment){
        databaseExecutor.execute(()->{
            assessmentByID = appDao.getAssessmentByID(assessment);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        return assessmentByID;
    }

    public List<AssessmentEntity> getAllAssessmentsCourse(int course){
        databaseExecutor.execute(()->{
            allAssessmentsCourse = appDao.getAllAssessmentsCourse(course);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        return allAssessmentsCourse;
    }


    public void insert(AssessmentEntity assessment){
        databaseExecutor.execute(()->{
            appDao.insert(assessment);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(AssessmentEntity assessment){
        databaseExecutor.execute(()->{
            appDao.update(assessment);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(AssessmentEntity assessment){
        databaseExecutor.execute(()->{
            appDao.delete(assessment);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

// Repo for note entity

    public List<NoteEntity> getAllNotes(){
        databaseExecutor.execute(()->{
            allNotes = appDao.getAllNotes();
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        return allNotes;
    }

    public List<NoteEntity> getNoteByID(int note){
        databaseExecutor.execute(()->{
            noteByID = appDao.getNoteByID(note);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        return noteByID;
    }

    public List<NoteEntity> getAllNotesAssessment(int assessment){
        databaseExecutor.execute(()->{
            allNotesAssessment = appDao.getAllNotesAssessment(assessment);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        return allNotesAssessment;
    }


    public void insert(NoteEntity note){
        databaseExecutor.execute(()->{
            appDao.insert(note);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(NoteEntity note){
        databaseExecutor.execute(()->{
            appDao.update(note);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(NoteEntity note){
        databaseExecutor.execute(()->{
            appDao.delete(note);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
