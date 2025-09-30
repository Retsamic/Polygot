package org.example;

import java.util.List;

public interface ISnippetService {
    Snippet createSnippet(Snippet snippet);
    Snippet updateSnippet(Snippet snippet);
    void deleteSnippet(Snippet snippet);
    Snippet findById(int id);
    List<Snippet> findAll();
    List<Snippet> findByName(String name);

}
