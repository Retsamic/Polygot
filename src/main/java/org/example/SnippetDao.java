package org.example;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SnippetDao implements ISnippetDao {

    private final JdbcTemplate jdbcTemplate;

    public SnippetDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createSnippet(Snippet snippet) {
        String sql = "INSERT INTO snippets(title, language, code_body, notes, created_at) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, snippet.getTitle(), snippet.getLanguage(), snippet.getCode_body(), snippet.getNotes(), snippet.getCreated_at());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM snippets WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateSnippet(Snippet snippet) {
        String sql = "UPDATE snippets SET title = ?, language = ?, code_body = ?, notes= ? WHERE id = ?";
        jdbcTemplate.update(sql, snippet.getTitle(), snippet.getLanguage(), snippet.getCode_body(), snippet.getNotes(), snippet.getId());
    }

    @Override
    public List<Snippet> findAll() {
        String sql = "SELECT * FROM snippets";
        return jdbcTemplate.query(sql, (rs, rowNum) -> toSnippet(rs));
    }

    @Override
    public Snippet findById(int id) {
        String sql = "SELECT * FROM snippets WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> toSnippet(rs));
    }

    @Override
    public Snippet findByName(String name) {
        String sql = "SELECT * FROM Snippets WHERE title LIKE ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name + '%'}, (rs, rowNum) -> toSnippet(rs));
    }

    private Snippet toSnippet(ResultSet rs) throws SQLException {
        Snippet snippet = new Snippet();
        snippet.setId(rs.getInt("id"));
        snippet.setTitle(rs.getString("title"));
        snippet.setLanguage(rs.getString("language"));
        snippet.setCode_body(rs.getString("code_body"));
        snippet.setNotes(rs.getString("notes"));
        snippet.setCreated_at(rs.getTimestamp("created_at"));
        return snippet;
    }
}