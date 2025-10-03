package org.example.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.transaction.Transactional;
import org.example.Entity.Snippet;
import org.example.Entity.Tag;
import org.example.Repository.SnippetRepository;
import org.example.Repository.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class SnippetService {
    private final SnippetRepository snippetRepository;
    private final TagRepository tagsDao;

    public SnippetService(TagRepository tagsDao, SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
        this.tagsDao = tagsDao;
    }

    public Snippet findSnippetById(long id) {
        return snippetRepository.findById(id).orElseThrow(() -> new RuntimeException("Snippet not found"));

    }

    public List<Tag> getTagsForSnippet(long snippetId) {
        Snippet snippet = findSnippetById(snippetId);
        return new ArrayList<>(snippet.getTags());
    }
    @Transactional
    public Snippet addTagToSnippet(long snippetId, long tagId) {
        Snippet snippet = findSnippetById(snippetId);
        Tag tag = tagsDao.findById(tagId).orElseThrow(() -> new RuntimeException("Tag not found"));
        snippet.getTags().add(tag);
        return snippetRepository.save(snippet);
    }

}
