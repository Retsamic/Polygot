package org.example.Service;

import org.example.Entity.Tag;
import org.example.Repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

}
