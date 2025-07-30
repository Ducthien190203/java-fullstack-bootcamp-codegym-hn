package vn.codegym.validatemusicinfo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vn.codegym.validatemusicinfo.model.Song;
import vn.codegym.validatemusicinfo.repository.SongRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SongServiceTest {

    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private SongServiceImpl songService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllShouldReturnAllSongs() {
        Song song1 = new Song("Song 1", "Artist 1", "Genre 1");
        Song song2 = new Song("Song 2", "Artist 2", "Genre 2");
        List<Song> expectedSongs = Arrays.asList(song1, song2);

        when(songRepository.findAll()).thenReturn(expectedSongs);

        List<Song> actualSongs = songService.findAll();

        assertNotNull(actualSongs);
        assertEquals(expectedSongs.size(), actualSongs.size());
        assertEquals(expectedSongs, actualSongs);
        verify(songRepository, times(1)).findAll();
    }

    @Test
    void findByIdShouldReturnCorrectSongWhenFound() {
        Long songId = 1L;
        Song expectedSong = new Song("Song 1", "Artist 1", "Genre 1");
        expectedSong.setId(songId);

        when(songRepository.findById(songId)).thenReturn(expectedSong);

        Song actualSong = songService.findById(songId);

        assertNotNull(actualSong);
        assertEquals(expectedSong, actualSong);
        verify(songRepository, times(1)).findById(songId);
    }

    @Test
    void findByIdShouldReturnNullWhenNotFound() {
        Long songId = 99L;

        when(songRepository.findById(songId)).thenReturn(null);

        Song actualSong = songService.findById(songId);

        assertNull(actualSong);
        verify(songRepository, times(1)).findById(songId);
    }

    @Test
    void saveShouldCallRepositorySave() {
        Song songToSave = new Song("New Song", "New Artist", "New Genre");

        songService.save(songToSave);

        verify(songRepository, times(1)).save(songToSave);
    }
}