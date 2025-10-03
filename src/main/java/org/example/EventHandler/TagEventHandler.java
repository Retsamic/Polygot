package org.example.EventHandler;
import org.example.Entity.Tag;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import org.example.Repository.TagRepository;

@Component
@RepositoryEventHandler
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

    @HandleBeforeSave
    public void handleBeforeSave(Tag tag) {
       Tag existingTag = tagRepository.getTagByName(tag.getName());
        if(existingTag != null && existingTag.getId() != tag.getId()) {
            throw new IllegalArgumentException("Tag already exists");
        }
    }
}
