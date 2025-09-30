package org.example;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TagsDAO implements ITagsDao{

    private final JdbcTemplate jdbcTemplate;

    public TagsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createTag(Tags tag) {
        String sql = "INSERT INTO tags (name) VALUES(?)";
        jdbcTemplate.update(sql, tag.getName());
    }

    @Override
    public Tags findByName(String name) {
        String sql = "SELECT * FROM tags WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, (rs, rowNum) -> toTags(rs));
    }

    @Override
    public Tags findById(int id) {
        String sql = "SELECT * FROM tags WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> toTags(rs));
    }

    @Override
    public void removeTag(int id) {
        String sql = "DELETE FROM tags WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateTag(Tags tag) {
        String sql = "UPDATE tags SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, tag.getName(), tag.getId());
    }


    private Tags toTags(ResultSet rs) throws SQLException {
        Tags tag = new Tags();
        tag.setId((rs.getInt("id")));
        tag.setName(rs.getString("name"));
        return tag;
    }
}