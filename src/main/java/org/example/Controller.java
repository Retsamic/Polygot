package org.example;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/snippets")
public class Controller {

    private final SnippetService snippetService;
    private final TagService tagService;

    public Controller(SnippetService snippetService, TagService tagService) {
        this.snippetService = snippetService;
        this.tagService = tagService;
    }

    @GetMapping
    public List<Snippet> getAllSnippets() {
        return snippetService.findAll();
    }

    @GetMapping("/{title}")
    public Snippet getSnippetByTitle(@PathVariable String title) {
        return snippetService.findSnippetByTitle(title);
    }

    @GetMapping("/{id}")
    public Snippet getSnippetById(@PathVariable int id) {
        return snippetService.findSnippetById(id);
    }

    @PostMapping
    public Snippet createSnippet(@RequestBody Snippet snippet) {
        return snippetService.createSnippet(snippet);
    }

    @PutMapping("/{id}")
    public Snippet updateSnippet(@PathVariable int id, @RequestBody Snippet snippet) {
        return snippetService.updateSnippet(snippet, id);
    }

    @DeleteMapping("/{id}")
    public void deleteSnippet(@PathVariable int id) {
        snippetService.deleteSnippet(id);
    }


    @GetMapping("/{snippetId}/tags")
    public List<Tag> getTagsForSnippet(@PathVariable int snippetId) {
        return snippetService.getTagsForSnippet(snippetId);
    }

    @PostMapping("/{snippetId}/tags/{tagId}")
    public Snippet addTagToSnippet(@PathVariable int snippetId, @PathVariable int tagId) {
        return snippetService.addTagToSnippet(snippetId, tagId);
    }


}