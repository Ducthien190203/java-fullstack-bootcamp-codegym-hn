package com.codegym.emailconfigmanager.controller;

import com.codegym.emailconfigmanager.model.MailSettings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    private MailSettings currentSettings = new MailSettings("English", 10, false, "Your signature here");

    @GetMapping
    public String showSettingsForm(Model model) {
        model.addAttribute("mailSettings", currentSettings);
        model.addAttribute("languages", new String[]{"English", "Vietnamese", "Japanese", "Chinese"});
        model.addAttribute("pageSizes", new int[]{5, 10, 15, 25, 50, 100});
        return "settings";
    }

    @PostMapping
    public String updateSettings(@ModelAttribute("mailSettings") MailSettings mailSettings) {
        this.currentSettings = mailSettings;
        return "redirect:/settings";
    }
}
