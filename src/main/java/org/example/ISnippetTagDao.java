package org.example;

import java.util.List;

public interface ISnippetTagDao {
    void addTagToSnippet(int snippetId, int tagId);
    void deleteTagFromSnippet(int snippetId, int tagId);
    List<Snippet> findSnippetsForTags(int tagId);
    List<Tags> findTagsForSnippets(int snippetId);
}