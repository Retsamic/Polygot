package org.example;

import java.util.List;

public interface ISnippetDao {
    void createNote (Snippet snippet);
    void delete(int id);
    void updateSnippet (Snippet snippet);
    List<Snippet> findAll();
    Snippet findById(int id);
    Snippet findByName(String name);

}
