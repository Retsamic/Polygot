package org.example;
import java.sql.*;

public class TagsDAO {
    private final DatabaseManager dbManager;
    public TagsDAO(DatabaseManager dbManager){ this.dbManager = dbManager;}

    public void createTag(Tags tag){
        String sql = "INSERT INTO tags (name) VALUES(?);";
        try(Connection conn = dbManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, tag.getName());
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Tags findByName(String name){
        Tags tags = null;
        String sql = "SELECT * FROM tags WHERE name = ?";

        try(Connection conn = dbManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setString(1,name);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    tags = new Tags();
                    tags.setId((rs.getInt("id")));
                    tags.setName(rs.getString("name"));
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return tags;
    }

    public Tags findById(int id){
        Tags tags = null;
        String sql = "SELECT * FROM tags WHERE id = ?";

        try(Connection conn = dbManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setInt(1,id);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    tags = new Tags();
                    tags.setId((rs.getInt("id")));
                    tags.setName(rs.getString("name"));
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return tags;
    }

    public void removeTag(int id){
        String sql = "DELETE FROM tags WHERE id = ?;";
        try(Connection conn = dbManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void updatetag(Tags tag){
        String sql = "UPDATE tags SET name = ? WHERE id = ?;";
        try(Connection conn = dbManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, tag.getName());
            ps.setInt(2, tag.getId());
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
