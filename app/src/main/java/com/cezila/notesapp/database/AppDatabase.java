package com.cezila.notesapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.cezila.notesapp.converter.DateConverter;

@Database(entities = {NoteEntity.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract NoteDao getNoteDao();

}
