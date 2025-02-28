package com.cezila.notesapp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "note_entity")
public class NoteEntity {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    public String title;

    public String text;

    @ColumnInfo(name = "create_at")
    public Date createdAt;

}
