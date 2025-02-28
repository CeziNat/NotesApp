package com.cezila.notesapp.converter;

import com.cezila.notesapp.database.NoteEntity;
import com.cezila.notesapp.model.Note;

public class NoteConverter {

    public static Note convertEntityToNote(NoteEntity entity) {
        return new Note(entity.title, entity.text, entity.createdAt, entity.uid);
    }

    public static NoteEntity convertNoteToEntity(Note note) {
        NoteEntity entity = new NoteEntity();
        entity.title = note.getTitle();
        entity.text = note.getText();
        entity.createdAt = note.getCreatedAt();
        return entity;
    }

}
