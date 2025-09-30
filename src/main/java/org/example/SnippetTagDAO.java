package org.example;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SnippetTagDAO implements ISnippetTagDao{

    private final JdbcTemplate jdbcTemplate;

    public SnippetTagDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addTagToSnippet(int snippetId, int tagId) {
        String sql = "INSERT INTO snippet_tags(snippet_id, tag_id) VALUES (?,?)";
        jdbcTemplate.update(sql, snippetId, tagId);
    }

    @Override
    public void deleteTagFromSnippet(int snippetId, int tagId) {
        String sql = "DELETE FROM snippet_tags WHERE snippet_id = ? AND tag_id = ?";
        jdbcTemplate.update(sql, snippetId, tagId);
    }

    @Override
    public List<Snippet> findSnippetsForTags(int tagId) {
        String sql = "SELECT s.* FROM snippets s JOIN snippet_tags st ON s.id = st.snippet_id WHERE st.tag_id = ?";
        return jdbcTemplate.query(sql, new Object[]{tagId}, (rs, rowNum) -> toSnippet(rs));
    }

    @Override
    public List<Tags> findTagsForSnippets(int snippetId) {
        String sql = "SELECT t.* FROM tags t JOIN snippet_tags st ON t.id = st.tag_id WHERE st.snippet_id = ?";
        return jdbcTemplate.query(sql, new Object[]{snippetId}, (rs, rowNum) -> toTags(rs));
    }


    private Snippet toSnippet(ResultSet rs) throws SQLException {
        Snippet snippet = new Snippet();
        snippet.setId(rs.getInt("id"));
        snippet.setTitle((rs.getString("title")));
        snippet.setLanguage(rs.getString("language"));
        snippet.setCode_body(rs.getString("code_body"));
        snippet.setNotes(rs.getString("notes"));
        snippet.setCreated_at(rs.getTimestamp("created_at"));
        return snippet;
    }

    private Tags toTags(ResultSet rs) throws SQLException {
        Tags tag = new Tags();
        tag.setName(rs.getString("name"));
        tag.setId(rs.getInt("id"));
        return tag;
    }
}