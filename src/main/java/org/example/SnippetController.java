package org.example;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/snippets")
public class SnippetController {

    private final SnippetDao snippetDAO;
    private final TagsDAO tagsDAO;
    private final SnippetTagDAO snippetTagDAO;

    public SnippetController(SnippetDao snippetDAO, TagsDAO tagsDAO, SnippetTagDAO snippetTagDAO) {
        this.snippetDAO = snippetDAO;
        this.tagsDAO = tagsDAO;
        this.snippetTagDAO = snippetTagDAO;
    }

    @GetMapping
    public List<Snippet> getAllSnippets() {
        return snippetDAO.findAll();
    }

    @GetMapping("/{id}")
    public Snippet getSnippetById(@PathVariable int id) {
        return snippetDAO.findById(id);
    }

    @PostMapping
    public void createSnippet(@RequestBody Snippet snippet) {
        snippetDAO.createSnippet(snippet);
    }

    @PutMapping("/{id}")
    public void updateSnippet(@PathVariable int id, @RequestBody Snippet snippet) {
        snippet.setId(id);
        snippetDAO.updateSnippet(snippet);
    }

    @DeleteMapping("/{id}")
    public void deleteSnippet(@PathVariable int id) {
        snippetDAO.delete(id);
    }

    @GetMapping("/{snippetId}/tags")
    public List<Tags> getTagsForSnippet(@PathVariable int snippetId) {
        return snippetTagDAO.findTagsForSnippets(snippetId);
    }

    @PostMapping("/{snippetId}/tags")
    public void addTagToSnippet(@PathVariable int snippetId, @RequestBody Tags tag) {
        Tags existingTag = tagsDAO.findByName(tag.getName());
        if (existingTag == null) {
            tagsDAO.createTag(tag);
            existingTag = tagsDAO.findByName(tag.getName());
        }
        snippetTagDAO.addTagToSnippet(snippetId, existingTag.getId());
    }


}