package org.example.Controller;

import org.example.Entity.Snippet;
import org.example.Service.SnippetService;
import org.example.Entity.Tag;
import org.example.Service.TagService;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


@RestController
@RequestMapping("/snippets")
public class SnippetController {

    private final SnippetService snippetService;
    private final TagService tagService;

    public SnippetController(SnippetService snippetService, TagService tagService) {
        this.snippetService = snippetService;
        this.tagService = tagService;
    }

    @GetMapping("/id/{snippetId}/tags")
    public List<Tag> getTagsForSnippet(@PathVariable long snippetId) {
        return snippetService.getTagsForSnippet(snippetId);
    }

    @PostMapping("/id/{snippetId}/tags/{tagId}")
    public Snippet addTagToSnippet(@PathVariable long snippetId, @PathVariable long tagId) {
        return snippetService.addTagToSnippet(snippetId, tagId);
    }



}