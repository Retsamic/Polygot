package org.example;

import java.util.ArrayList;
import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class SnippetService implements ISnippetService {
    private final SnippetRepository snippetDao;
    private final TagRepository tagsDao;

    public SnippetService(SnippetRepository snippetDao, TagRepository tagsDao) {
        this.snippetDao = snippetDao;
        this.tagsDao = tagsDao;
    }

    @Override
    public Snippet createSnippet(Snippet snippet) {
        if(snippetDao.findSnippetByTitle(snippet.getTitle()) != null){
            throw new IllegalArgumentException("Title already exists");
        }
       snippetDao.save(snippet);
       return snippet;
    }

    public Snippet updateSnippet(Snippet snippet, int id) {
        Snippet existingSnippet = findSnippetById(id);
        existingSnippet.setTitle(snippet.getTitle());
        existingSnippet.setLanguage(snippet.getLanguage());
        existingSnippet.setCodeBody(snippet.getCodeBody());
        existingSnippet.setNotes(snippet.getNotes());
        return snippetDao.save(existingSnippet);
    }

    @Transactional
    public void deleteSnippet(int id) {
        if(!snippetDao.existsById(id)){
            throw new IllegalArgumentException("Snippet with id " + id + " does not exist");
        }
        snippetDao.deleteById(id);
    }


    public Snippet findSnippetById(int id) {
        return snippetDao.findById(id).orElseThrow(() -> new RuntimeException("Snippet not found"));

    }

    @Override
    public List<Snippet> findAll() {
        return snippetDao.findAll();
    }

    public Snippet findSnippetByTitle(String name) {
        return snippetDao.findSnippetByTitle(name);
    }

    public List<Tag> getTagsForSnippet(int snippetId) {
        Snippet snippet = findSnippetById(snippetId);
        return new ArrayList<>(snippet.getTags());
    }
    @Transactional
    public Snippet addTagToSnippet(int snippetId, int tagId) {
        Snippet snippet = findSnippetById(snippetId);
        Tag tag = tagsDao.findById(tagId).orElseThrow(() -> new RuntimeException("Tag not found"));
        snippet.getTags().add(tag);
        return snippetDao.save(snippet);
    }
}
