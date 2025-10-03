package org.example.repository;

import org.example.model.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnippetRepository extends JpaRepository<Snippet, Integer> {
    public Snippet findSnippetByTitle(String title);

}
