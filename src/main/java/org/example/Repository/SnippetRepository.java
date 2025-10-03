package org.example.Repository;

import org.example.Entity.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.sql.Timestamp;
import java.util.List;

@RepositoryRestResource(path = "snippets", collectionResourceRel = "snippets")
public interface SnippetRepository extends JpaRepository<Snippet, Long> {
    public Snippet findSnippetByTitle(String title);
    List<Snippet> findByLanguage(String language);
    boolean existsByTitle(String title);
    List<Snippet> findByCreatedAtAfter(Timestamp date);
    List<Snippet> findByCreatedAtBefore(Timestamp date);
    List<Snippet> findByCreatedAtBetween(Timestamp date1, Timestamp date2);

    List<Snippet> findByAllTags(@Param("tagIds") List<Integer> tagIds, @Param("tagCount") long tagCount);


}
