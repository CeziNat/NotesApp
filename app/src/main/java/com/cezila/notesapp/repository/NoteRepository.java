package com.cezila.notesapp.repository;

import com.cezila.notesapp.model.Note;

import java.util.List;

/**
 * NOSSA REGRA DE NEGOCIO: SALVAR (CREATE), BUSCAR(READ), DELETAR (DELETE)
 */
public interface NoteRepository {

    void saveNote(Note note);

    List<Note> getAllNotes();

    void deleteNote(Note note);

}
