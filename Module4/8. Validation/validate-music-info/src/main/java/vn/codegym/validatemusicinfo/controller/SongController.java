package vn.codegym.validatemusicinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import vn.codegym.validatemusicinfo.model.Song;
import vn.codegym.validatemusicinfo.service.SongService;

import javax.validation.Valid;

@Controller
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping("/songs")
    public String listSongs(Model model) {
        model.addAttribute("songs", songService.findAll());
        return "list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("song", new Song());
        return "create";
    }

    @PostMapping("/create")
    public String createSong(@Valid @ModelAttribute("song") Song song, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create";
        }
        songService.save(song);
        return "redirect:/songs";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Song song = songService.findById(id);
        model.addAttribute("song", song);
        return "edit";
    }

    /**
     * Processes the submission of the edit song form.
     *
     * @param song The Song object populated from the form.
     * @param bindingResult The BindingResult for validation errors.
     * @return A redirect to the songs list if successful, or back to the edit form if validation fails.
     */
    @PostMapping("/edit")
    public String updateSong(@Valid @ModelAttribute("song") Song song, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        songService.save(song);
        return "redirect:/songs";
    }
}