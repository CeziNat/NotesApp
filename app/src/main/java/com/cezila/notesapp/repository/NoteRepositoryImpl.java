package com.cezila.notesapp.repository;

import com.cezila.notesapp.converter.NoteConverter;
import com.cezila.notesapp.database.NoteDao;
import com.cezila.notesapp.database.NoteEntity;
import com.cezila.notesapp.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteRepositoryImpl implements NoteRepository {

    private final NoteDao dao;

    public NoteRepositoryImpl(NoteDao noteDao) {
        this.dao = noteDao;
    }

    @Override
    public void saveNote(Note note) {
        dao.insertNote(NoteConverter.convertNoteToEntity(note));
    }

    @Override
    public List<Note> getAllNotes() {
        List<NoteEntity> allNotesEntity = dao.getAllNotes();
        List<Note> notes = new ArrayList<>(); // Inicia uma lista vazia

        for (int position = 0; position < allNotesEntity.size(); position = position + 1) {
            NoteEntity entity = allNotesEntity.get(position);
            Note note = NoteConverter.convertEntityToNote(entity);
            notes.add(note);
        }

        return notes;
    }

    @Override
    public void deleteNote(Note note) {
        NoteEntity noteEntity = NoteConverter.convertNoteToEntity(note);
        noteEntity.uid = note.getId();
        dao.deleteNote(noteEntity);
    }

}
