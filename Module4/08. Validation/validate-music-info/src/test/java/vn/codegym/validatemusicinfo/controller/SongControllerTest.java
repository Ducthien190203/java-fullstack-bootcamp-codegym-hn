package vn.codegym.validatemusicinfo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.mockito.ArgumentCaptor;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import vn.codegym.validatemusicinfo.WebConfig;
import vn.codegym.validatemusicinfo.model.Song;
import vn.codegym.validatemusicinfo.service.SongService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, SongControllerTest.TestConfig.class})
class SongControllerTest {

    @Mock
    private SongService songService;

    @Autowired
    private SongController songController;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private Song song1;
    private Song song2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        song1 = new Song("Song A", "Artist X", "Pop");
        song1.setId(1L);
        song2 = new Song("Song B", "Artist Y", "Rock");
        song2.setId(2L);
    }

    @Test
    void listSongsShouldReturnListViewWithSongs() throws Exception {
        List<Song> allSongs = Arrays.asList(song1, song2);
        when(songService.findAll()).thenReturn(allSongs);

        mockMvc.perform(get("/songs"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(model().attributeExists("songs"))
                .andExpect(model().attribute("songs", allSongs));

        verify(songService, times(1)).findAll();
    }

    @Test
    void showCreateFormShouldReturnCreateView() throws Exception {
        mockMvc.perform(get("/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("create"))
                .andExpect(model().attributeExists("song"));
    }

    @Test
    void createSongShouldRedirectToListOnSuccess() throws Exception {
        Song newSong = new Song("New Song", "New Artist", "New Genre");

        mockMvc.perform(post("/create")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", newSong.getName())
                        .param("artist", newSong.getArtist())
                        .param("genre", newSong.getGenre()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/songs"));

        ArgumentCaptor<Song> songCaptor = ArgumentCaptor.forClass(Song.class);
        verify(songService, times(1)).save(songCaptor.capture());
        Song capturedSong = songCaptor.getValue();
        assertEquals(newSong.getName(), capturedSong.getName());
        assertEquals(newSong.getArtist(), capturedSong.getArtist());
        assertEquals(newSong.getGenre(), capturedSong.getGenre());
    }

    @Test
    void createSongShouldReturnCreateViewOnValidationErrors() throws Exception {
        mockMvc.perform(post("/create")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "")
                        .param("artist", "")
                        .param("genre", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("create"))
                .andExpect(model().attributeHasErrors("song"));

        verify(songService, never()).save(any(Song.class));
    }

    @Test
    void showEditFormShouldReturnEditViewWithSong() throws Exception {
        when(songService.findById(1L)).thenReturn(song1);

        mockMvc.perform(get("/edit/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"))
                .andExpect(model().attributeExists("song"))
                .andExpect(model().attribute("song", song1));

        verify(songService, times(1)).findById(1L);
    }

    @Test
    void updateSongShouldRedirectToListOnSuccess() throws Exception {
        Song updatedSong = new Song("Updated Song", "Updated Artist", "Updated Genre");
        updatedSong.setId(1L);

        mockMvc.perform(post("/edit")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", updatedSong.getId().toString())
                        .param("name", updatedSong.getName())
                        .param("artist", updatedSong.getArtist())
                        .param("genre", updatedSong.getGenre()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/songs"));

        ArgumentCaptor<Song> songCaptor = ArgumentCaptor.forClass(Song.class);
        verify(songService, times(1)).save(songCaptor.capture());
        Song capturedSong = songCaptor.getValue();
        assertEquals(updatedSong.getId(), capturedSong.getId());
        assertEquals(updatedSong.getName(), capturedSong.getName());
        assertEquals(updatedSong.getArtist(), capturedSong.getArtist());
        assertEquals(updatedSong.getGenre(), capturedSong.getGenre());
    }

    @Test
    void updateSongShouldReturnEditViewOnValidationErrors() throws Exception {
        mockMvc.perform(post("/edit")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "1") // ID is needed for edit, even if other fields are invalid
                        .param("name", "")
                        .param("artist", "")
                        .param("genre", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("edit"))
                .andExpect(model().attributeHasErrors("song"));

        verify(songService, never()).save(any(Song.class));
    }

    @Configuration
    static class TestConfig {
        @Autowired
        private SongService songServiceMock; // This will be our @Mock songService

        @Bean
        @Primary
        public SongService songService() {
            return songServiceMock;
        }
    }
}
