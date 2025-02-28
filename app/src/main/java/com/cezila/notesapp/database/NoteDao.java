package com.cezila.notesapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note_entity")
    List<NoteEntity> getAllNotes();

    @Query("SELECT * FROM note_entity WHERE title LIKE '%' || :title || '%'")
    List<NoteEntity> getNoteByTitle(String title);

    @Insert
    void insertNote(NoteEntity note);

    @Delete
    void deleteNote(NoteEntity note);

}
