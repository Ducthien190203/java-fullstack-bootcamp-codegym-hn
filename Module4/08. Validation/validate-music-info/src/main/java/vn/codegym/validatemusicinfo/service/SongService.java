package vn.codegym.validatemusicinfo.service;

import vn.codegym.validatemusicinfo.model.Song;

import java.util.List;

public interface SongService {
    List<Song> findAll();

    Song findById(Long id);

    /**
     * Saves a song. If the song has an ID, it updates the existing song;
     * otherwise, it creates a new song.
     *
     * @param song The song to save.
     */
    void save(Song song);
}
