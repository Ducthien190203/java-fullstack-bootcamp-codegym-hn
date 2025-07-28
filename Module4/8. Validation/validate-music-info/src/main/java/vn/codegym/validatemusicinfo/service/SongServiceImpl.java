package vn.codegym.validatemusicinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.validatemusicinfo.model.Song;
import vn.codegym.validatemusicinfo.repository.SongRepository;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public Song findById(Long id) {
        return songRepository.findById(id);
    }

    /**
     * Saves a song using the underlying repository. This method handles both creation and update operations.
     *
     * @param song The song to save.
     */
    @Override
    public void save(Song song) {
        songRepository.save(song);
    }
}