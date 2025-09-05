package org.example;
import java.sql.*;
import java.util.*;

public class SnippetDAO {
    private final DatabaseManager dbManager;

    public SnippetDAO(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void createNote(Snippet snippet) {

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

    public void delete(int id) {
        String sql = "DELETE FROM snippets WHERE id = ?";
        try(Connection conn = dbManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Snippet findByName(String name){
        String sql = "SELECT * FROM Snippets WHERE title LIKE ?;";
        Snippet snippets = null;
        try(Connection conn = dbManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,name + '%');

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    snippets = new Snippet();
                    snippets.setTitle(rs.getString("title"));
                    snippets.setLanguage(rs.getString("language"));
                    snippets.setCode_body(rs.getString("code_body"));
                    snippets.setNotes(rs.getString("notes"));
                    snippets.setCreated_at(rs.getTimestamp("created_at"));
                }
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return snippets;
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
