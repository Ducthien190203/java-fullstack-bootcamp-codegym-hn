package vn.codegym.validatemusicinfo.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vn.codegym.validatemusicinfo.model.Song;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SongRepositoryImplTest {

    private SongRepositoryImpl songRepository;

    @BeforeEach
    void setUp() {
        SongRepositoryImpl.resetData(); // Reset static data before each test
        songRepository = new SongRepositoryImpl();
    }

    @Test
    void findAllShouldReturnAllSongs() {
        List<Song> songs = songRepository.findAll();
        assertNotNull(songs);
        // Add more specific assertions based on initial data in SongRepositoryImpl
        // For example, assertEquals(2, songs.size());
    }

    @Test
    void findByIdShouldReturnCorrectSongWhenFound() {
        // Assuming song with ID 1L exists
        Song song = songRepository.findById(1L);
        assertNotNull(song);
        assertEquals(1L, song.getId());
        // Add more assertions for song properties
    }

    @Test
    void findByIdShouldReturnNullWhenNotFound() {
        Song song = songRepository.findById(99L); // Assuming 99L does not exist
        assertNull(song);
    }

    @Test
    void findByIdShouldThrowNullPointerExceptionWhenIdIsNull() {
        assertThrows(NullPointerException.class, () -> songRepository.findById(null));
    }

    @Test
    void saveShouldAddNewSongWhenIdIsNull() {
        Song newSong = new Song("New Song", "New Artist", "New Genre");
        songRepository.save(newSong);
        assertNotNull(newSong.getId()); // ID should be generated
        assertNotNull(songRepository.findById(newSong.getId())); // Should be retrievable
    }

    @Test
    void saveShouldUpdateExistingSongWhenIdIsNotNull() {
        Song existingSong = songRepository.findById(1L);
        assertNotNull(existingSong);
        String originalName = existingSong.getName();

        existingSong.setName("Updated Song Name");
        existingSong.setArtist("Updated Artist");
        existingSong.setGenre("Updated Genre");
        songRepository.save(existingSong);

        Song updatedSong = songRepository.findById(1L);
        assertNotNull(updatedSong);
        assertEquals("Updated Song Name", updatedSong.getName());
        assertEquals("Updated Artist", updatedSong.getArtist());
        assertEquals("Updated Genre", updatedSong.getGenre());
        assertNotEquals(originalName, updatedSong.getName());
        assertEquals(2, songRepository.findAll().size()); // Total songs should remain 2
    }

    @Test
    void saveShouldThrowNullPointerExceptionWhenSongIsNull() {
        assertThrows(NullPointerException.class, () -> songRepository.save(null));
    }
}
