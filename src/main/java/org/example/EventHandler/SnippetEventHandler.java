package org.example.EventHandler;
import org.example.Entity.Snippet;
import org.example.Repository.SnippetRepository;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
@RepositoryEventHandler
public class SnippetEventHandler {
    private final SnippetRepository snippetRepository;

    public SnippetEventHandler(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    @HandleBeforeCreate
    public void handleSnippetCreate(Snippet snippet) {
        if(snippetRepository.findSnippetByTitle(snippet.getTitle()) != null){
            throw new IllegalArgumentException("Snippet title already exists");
        }
        snippet.setCreatedAt(Timestamp.from(Instant.now()));
    }

    @HandleBeforeSave
    public void handleSnippetUpdate(Snippet snippet) {
        Snippet existingSnippet = snippetRepository.findSnippetByTitle(snippet.getTitle());

        if(existingSnippet != null && existingSnippet.getId() != snippet.getId()){
            throw new IllegalArgumentException("Title already exists");
        }
    }

    @HandleBeforeDelete
    public void handleSnippetDelete(Snippet snippet) {
        Snippet existingSnippet = snippetRepository.findSnippetByTitle(snippet.getTitle());
        if(existingSnippet == null){
            throw new IllegalArgumentException("Snippet does not exist");
        }
    }

    @HandleBeforeDelete
    public void handleSnippetDeleteTags(Snippet snippet) {
        if(snippet.getTags() != null && !snippet.getTags().isEmpty()){
            throw new IllegalArgumentException("Snippet still has tags");
        }
    }

}
