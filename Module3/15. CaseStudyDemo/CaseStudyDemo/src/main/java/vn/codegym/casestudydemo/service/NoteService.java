package vn.codegym.casestudydemo.service;

import vn.codegym.casestudydemo.model.Note;

import java.sql.Connection;
import java.util.List;

public interface NoteService {
    List<Note> searchNotes(Connection conn, String keyword);

    void addNote(Connection conn, Note note);

    void removeNote(Connection conn, int noteId);
}
