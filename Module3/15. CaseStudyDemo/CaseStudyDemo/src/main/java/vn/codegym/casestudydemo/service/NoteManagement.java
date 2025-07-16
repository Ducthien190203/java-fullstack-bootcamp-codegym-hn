import vn.codegym.casestudydemo.model.Note;
import vn.codegym.casestudydemo.service.NoteService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteServiceImpl implements NoteService {

    @Override
    public List<Note> searchNotes(Connection conn, String keyword) {
        List<Note> results = new ArrayList<>();
        String sql = "select title,content,type_id from note where title like ? or content like ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            String wildcard = "%" + keyword + "%";
            stmt.setString(1, wildcard);
            stmt.setString(2, wildcard);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Note note = new Note();
                note.setTypeId(rs.getInt("typeId"));
                note.setTitle(rs.getString("title"));
                note.setContent(rs.getString("content"));
                results.add(note);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    @Override
    public void addNote(Connection conn, Note note) {
        note.save(conn);
    }


    @Override
    public void removeNote(Connection conn, int noteId) {
        String sql = "delete from note where id =?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, noteId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}