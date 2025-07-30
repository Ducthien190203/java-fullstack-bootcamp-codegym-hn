package vn.codegym.validatemusicinfo.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Song {

    private Long id;

    /**
     * The name of the song.
     * Must not be empty, max 800 characters, and contain only letters, numbers, and spaces.
     */
    @NotEmpty(message = "Tên bài hát không được để trống.")
    @Size(max = 800, message = "Tên bài hát không được vượt quá 800 ký tự.")
    @Pattern(regexp = "^[\\p{L}\\p{M}0-9\\s]*$", message = "Tên bài hát chỉ được chứa chữ cái, số và khoảng trắng.")
    private String name;

    /**
     * The artist of the song.
     * Must not be empty, max 300 characters, and contain only letters, numbers, and spaces.
     */
    @NotEmpty(message = "Nghệ sĩ không được để trống.")
    @Size(max = 300, message = "Nghệ sĩ không được vượt quá 300 ký tự.")
    @Pattern(regexp = "^[\\p{L}\\p{M}0-9\\s]*$", message = "Tên nghệ sĩ chỉ được chứa chữ cái, số và khoảng trắng.")
    private String artist;

    /**
     * The genre(s) of the song.
     * Must not be empty, max 1000 characters, and contain only letters, numbers, commas, and spaces.
     */
    @NotEmpty(message = "Thể loại không được để trống.")
    @Size(max = 1000, message = "Thể loại không được vượt quá 1000 ký tự.")
    @Pattern(regexp = "^[\\p{L}\\p{M}0-9,\\s]*$", message = "Thể loại chỉ được chứa chữ cái, số, dấu phẩy và khoảng trắng.")
    private String genre;

    public Song() {
    }

    public Song(String name, String artist, String genre) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}