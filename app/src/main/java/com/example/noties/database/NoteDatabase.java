package com.example.noties.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.noties.dao.NoteDao;
import com.example.noties.entities.Note;

@Database(entities = Note.class, version = 1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    public static NoteDatabase noteDatabase;
    public static synchronized NoteDatabase getDatabase(Context context)
    {
        if(noteDatabase == null)
        {
            noteDatabase = Room.databaseBuilder(
                    context,NoteDatabase.class,
                    "notes_db"
            ).build();
        }
        return noteDatabase;
    }
    public abstract NoteDao noteDao();
}
