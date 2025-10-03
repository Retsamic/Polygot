package org.example;

import java.util.List;

public interface ISnippetService {
    Snippet createSnippet(Snippet snippet);
    Snippet updateSnippet(Snippet snippet, int id);
    void deleteSnippet(int id);
    Snippet findSnippetById(int id);
    List<Snippet> findAll();
    Snippet findSnippetByTitle(String name);

}
