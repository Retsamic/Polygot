package org.example;
import java.sql.*;
import java.util.*;

public class SnippetDAO {
    private final DatabaseManager dbManager;

    public SnippetDAO(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void save(Snippet snippet) {

        String sql = "INSERT INTO snippets(title, language, code_body, notes, created_at) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1,snippet.getTitle());
            ps.setString(2,snippet.getLanguage());
            ps.setString(3,snippet.getCode_body());
            ps.setString(4, snippet.getNotes());
            ps.setTimestamp(5, snippet.getCreated_at());
            ps.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(Snippet snippet) {
        String sql = "DELETE FROM snippets WHERE id = ?";
        try(Connection conn = dbManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1, snippet.getId());
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Snippet> findAll() {
        List<Snippet> snippets = new ArrayList<>();
        String sql = "SELECT * FROM snippets";

        try(Connection conn = dbManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()) {
                Snippet snippet = new Snippet();
                snippet.setId(rs.getInt("id"));
                snippet.setTitle(rs.getString("title"));
                snippet.setLanguage(rs.getString("language"));
                snippet.setCode_body(rs.getString("code_body"));
                snippet.setNotes(rs.getString("notes"));
                snippet.setCreated_at(rs.getTimestamp("created_at"));
                snippets.add(snippet);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }


        return snippets;
    }

    public Snippet findById(int id) {
        String sql = "SELECT * FROM snippets WHERE id = ?";
        Snippet snippet = null;
        try(Connection conn = dbManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    snippet = new Snippet();
                    snippet.setId(rs.getInt("id"));
                    snippet.setTitle(rs.getString("title"));
                    snippet.setLanguage(rs.getString("language"));
                    snippet.setCode_body(rs.getString("code_body"));
                    snippet.setNotes(rs.getString("notes"));
                    snippet.setCreated_at(rs.getTimestamp("created_at"));
                }
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return snippet;
    }

    public void updateSnippet(Snippet snippet){
        String sql = "UPDATE snippets SET title = ?, language = ?, code_body = ?, notes= ? WHERE id = ?";
        try(Connection conn = dbManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,snippet.getTitle());
            ps.setString(2, snippet.getLanguage());
            ps.setString(3, snippet.getCode_body());
            ps.setString(4, snippet.getNotes());
            ps.setInt(5,snippet.getId());
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();

        }


    }

}
