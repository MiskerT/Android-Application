package com.example.c196_misker_tsegaye.All.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.c196_misker_tsegaye.All.DAO.AppDao;
import com.example.c196_misker_tsegaye.All.Entities.AssessmentEntity;
import com.example.c196_misker_tsegaye.All.Entities.CourseEntity;
import com.example.c196_misker_tsegaye.All.Entities.NoteEntity;
import com.example.c196_misker_tsegaye.All.Entities.TermEntity;

@Database(entities = {TermEntity.class, CourseEntity.class, AssessmentEntity.class, NoteEntity.class} , version = 14, exportSchema = false)
public abstract class appDatabaseBuilder extends RoomDatabase{
    public abstract AppDao appDao();

    public static volatile appDatabaseBuilder INSTANCE;

// build database

    static appDatabaseBuilder getDatabase(final Context context){
        if(INSTANCE == null)
        {
            synchronized (appDatabaseBuilder.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), appDatabaseBuilder.class, "AppDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
