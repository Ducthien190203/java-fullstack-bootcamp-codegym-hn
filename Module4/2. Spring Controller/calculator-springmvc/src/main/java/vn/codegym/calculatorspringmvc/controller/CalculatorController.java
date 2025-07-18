package vn.codegym.calculatorspringmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @GetMapping("/calculator")
    public String showCalculator() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam("firstOperand") double firstOperand,
                            @RequestParam("secondOperand") double secondOperand,
                            @RequestParam("operator") String operator,
                            Model model) {
        double result = 0;
        String errorMessage = null;

        switch (operator) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
            case "/":
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    errorMessage = "Không thể chia cho 0";
                }
                break;
            default:
                errorMessage = "Toán tử không hợp lệ";
        }

        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        } else {
            model.addAttribute("result", result);
        }
        model.addAttribute("firstOperand", firstOperand);
        model.addAttribute("secondOperand", secondOperand);
        model.addAttribute("operator", operator);

        return "index";
    }
}