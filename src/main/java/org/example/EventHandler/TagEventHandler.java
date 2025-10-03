package org.example.EventHandler;
import org.example.Entity.Tag;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import org.example.Repository.TagRepository;

public class TagEventHandler {
    private final TagRepository tagRepository;

    public TagEventHandler(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @HandleBeforeCreate
    public void handleBeforeCreate(Tag tag) {
        if(tagRepository.getTagByName(tag.getName()) != null) {
            throw new IllegalArgumentException("Tag already exists");
        }
    }
}
