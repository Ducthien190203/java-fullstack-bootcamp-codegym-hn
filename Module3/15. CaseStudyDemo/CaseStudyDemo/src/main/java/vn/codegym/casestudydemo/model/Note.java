package vn.codegym.casestudydemo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Note {
    private int typeId;
    private String title;
    private String content;

    // Constructors
    public Note() {
    }

    public Note(String title) {
        this.title = title;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getType(Connection conn) {
        String typeName = null;
        String sql = "SELECT name FROM note_type WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, typeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                typeName = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeName;
    }


    public boolean save(Connection conn) {
        String sql = "insert into note (title,content,type_id) values(?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, content);
            stmt.setInt(3, typeId);
            stmt.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Connection conn) {
        String sql = "delete from note where title =? and content=? and type_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, content);
            stmt.setInt(3, typeId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
