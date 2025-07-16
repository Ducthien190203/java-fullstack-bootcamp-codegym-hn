package vn.codegym.thuchanh2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CurrencyConverterController {

    @GetMapping("/") // Thêm phương thức này để chuyển hướng từ root URL
    public String redirectToConverter() {
        return "redirect:/convert";
    }

    @GetMapping("/convert")
    public String showConverterForm() {
        return "converter"; // Trả về tên view "converter.jsp"
    }

    @PostMapping("/convert")
    public String convertCurrency(@RequestParam("usdAmount") double usdAmount,
                                  @RequestParam("rate") double rate,
                                  Model model) {
        double vndAmount = usdAmount * rate;
        model.addAttribute("usdAmount", usdAmount);
        model.addAttribute("rate", rate);
        model.addAttribute("vndAmount", vndAmount);
        return "result"; // Trả về tên view "result.jsp"
    }
}