package com.cezila.notesapp.model;

import java.util.Date;

/**
 * Classe modelo serve para representar algo. A classe não possui funções, apenas GETTER e SETTER.
 */
public class Note {

    private int id;
    private final String title;
    private final String text;
    private final Date createdAt;

    /**
     * Para criar um objeto dessa classe, eh obrigatorio passar TITLE e TEXT
     *
     * @param title - Titulo da Nota
     * @param text  - Texto da Nota
     */
    public Note(String title, String text) {
        this.title = title;
        this.text = text;
        this.createdAt = new Date();
    }

    public Note(String title, String text, Date createdAt, int id) {
        this.title = title;
        this.text = text;
        this.createdAt = createdAt;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }
}
