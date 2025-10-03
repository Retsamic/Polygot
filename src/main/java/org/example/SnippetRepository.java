package org.example;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SnippetRepository extends JpaRepository<Snippet, Integer> {
    public Snippet findSnippetByTitle(String title);

}
