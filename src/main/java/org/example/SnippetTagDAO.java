package org.example;
import java.sql.*;
import java.util.*;

public class SnippetTagDAO {
    private final DatabaseManager dbManager;
    public SnippetTagDAO(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void addTagToSnippet(int snippetId, int tagId){
        String sql = "INSERT INTO snippet_tags(snippet_id, tag_id) VALUES (?,?);";
        try(Connection conn = dbManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, snippetId);
            ps.setInt(2, tagId);
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Snippet> findSnippetsForTags(int tagId){
        List<Snippet> snippets = new ArrayList<>();
        String sql = "SELECT s.* FROM snippets s JOIN snippet_tags st ON s.i = st.snippet_id WHERE st.tag_id = ?;";
        try(Connection conn = dbManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, tagId);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Snippet snippet = new Snippet();
                    snippet.setId(rs.getInt("id"));
                    snippet.setTitle((rs.getString("title")));
                    snippet.setLanguage(rs.getString("language"));
                    snippet.setCode_body(rs.getString("cody_body"));
                    snippet.setNotes(rs.getString("notes"));
                    snippet.setCreated_at(rs.getTimestamp("created_at"));
                    snippets.add(snippet);
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return snippets;
    }

    public List<Tags> findTagsForSnippets(int snippetId){
        List<Tags> tags = new ArrayList<>();
        String sql = "SELECT t.* FROM tags t JOIN snippet_tags st ON t.id = st.tag_id WHERE st.snippet_id = ?;";
        try(Connection conn = dbManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, snippetId);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Tags tag = new Tags();
                    tag.setName(rs.getString("name"));
                    tag.setId(rs.getInt("id"));
                    tags.add(tag);
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return tags;
    }

}
