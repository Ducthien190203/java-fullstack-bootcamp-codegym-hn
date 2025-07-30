package vn.codegym.validatemusicinfo.repository;

import org.springframework.stereotype.Repository;
import vn.codegym.validatemusicinfo.model.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SongRepositoryImpl implements SongRepository {

    private static final List<Song> songs = new ArrayList<>();
    private static final AtomicLong counter = new AtomicLong(2L);

    /**
     * Resets the in-memory song data to its initial state.
     * This method is primarily used for testing purposes to ensure test isolation.
     */
    public static void resetData() {
        songs.clear();
        counter.set(0L); // Reset counter to 0, so first new song gets ID 1
        Song song1 = new Song("Tên Bài Hát 1", "Nghệ Sĩ 1", "Pop, Rock");
        song1.setId(1L);
        songs.add(song1);

        Song song2 = new Song("Tên Bài Hát 2", "Nghệ Sĩ 2", "Jazz");
        song2.setId(2L);
        songs.add(song2);
        counter.set(2L); // Set counter to the last ID used
    }

    static {
        resetData(); // Initialize data when class loads
    }

    @Override
    public List<Song> findAll() {
        return new ArrayList<>(songs);
    }

    /**
     * Finds a song by its ID from the in-memory list.
     *
     * @param id The ID of the song to find. Must not be null.
     * @return The Song object if found, otherwise null.
     * @throws NullPointerException if the provided ID is null.
     */
    @Override
    public Song findById(Long id) {
        Objects.requireNonNull(id, "Song ID cannot be null");
        return songs.stream()
                .filter(song -> id.equals(song.getId()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Saves a song to the in-memory list. If the song's ID is null, a new ID is generated and the song is added.
     * If the song has an ID, it attempts to update the existing song in the list.
     *
     * @param song The song object to save. Must not be null.
     * @throws NullPointerException if the provided song object is null.
     */
    @Override
    public void save(Song song) {
        Objects.requireNonNull(song, "Song object cannot be null");
        if (song.getId() == null) {
            song.setId(counter.incrementAndGet());
            songs.add(song);
        } else {
            Song existingSong = findById(song.getId());
            if (existingSong != null) {
                int index = songs.indexOf(existingSong);
                songs.set(index, song);
            }
        }
    }
}