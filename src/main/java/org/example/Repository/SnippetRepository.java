package org.example.Repository;

import org.example.Entity.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "snippets", collectionResourceRel = "snippets")
public interface SnippetRepository extends JpaRepository<Snippet, Long> {
    public Snippet findSnippetByTitle(String title);

}
