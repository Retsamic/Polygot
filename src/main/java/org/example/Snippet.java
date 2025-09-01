package org.example;

import java.sql.Timestamp;

public class Snippet {
    private int id;
    private String title;
    private String language;
    private String code_body;
    private String notes;
    private Timestamp created_at;

    public Snippet() {}

    public Snippet(int id, String title, String language, String code_body, String notes, Timestamp created_at) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.code_body = code_body;
        this.notes = notes;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getCode_body() {
        return code_body;
    }
    public void setCode_body(String code_body) {
        this.code_body = code_body;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public Timestamp getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Snippet{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}


