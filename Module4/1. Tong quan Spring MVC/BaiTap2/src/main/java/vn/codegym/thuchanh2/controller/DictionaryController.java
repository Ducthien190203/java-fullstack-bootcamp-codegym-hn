package vn.codegym.thuchanh2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DictionaryController {

    private static final Map<String, String> dictionary = new HashMap<>();

    static {
        dictionary.put("hello", "xin chào");
        dictionary.put("world", "thế giới");
        dictionary.put("computer", "máy tính");
        dictionary.put("programming", "lập trình");
        dictionary.put("java", "java");
    }

    @GetMapping("/") // Chuyển hướng từ root URL đến trang từ điển
    public String redirectToDictionary() {
        return "redirect:/dictionary";
    }

    @GetMapping("/dictionary")
    public String showDictionaryForm() {
        return "dictionary"; // Trả về tên view "dictionary.jsp"
    }

    @PostMapping("/dictionary")
    public String searchWord(@RequestParam("englishWord") String englishWord, Model model) {
        String vietnameseMeaning = dictionary.get(englishWord.toLowerCase()); // Chuyển về chữ thường để tìm kiếm không phân biệt hoa thường

        model.addAttribute("englishWord", englishWord);

        if (vietnameseMeaning != null) {
            model.addAttribute("vietnameseMeaning", vietnameseMeaning);
        } else {
            model.addAttribute("message", "Không tìm thấy từ '" + englishWord + "'.");
        }
        return "dictionary"; // Trả về lại trang dictionary.jsp để hiển thị kết quả
    }
}