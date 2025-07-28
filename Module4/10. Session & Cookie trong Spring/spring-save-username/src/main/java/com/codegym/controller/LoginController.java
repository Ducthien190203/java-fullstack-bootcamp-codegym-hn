package com.codegym.controller;

import com.codegym.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 @Controller @SessionAttributes("user")
public class LoginController {

    /*add user in model attribute*/
    @ModelAttribute("user")
    public User setUpUserForm() {
       return new User();
    }

    @RequestMapping("/login")
    public String Index( @CookieValue(value = "setUser", defaultValue = "") String setUser, Model model) {
       Cookie cookie = new Cookie("setUser", setUser);
       model.addAttribute("cookieValue", cookie);
       return "/login";
    }

    @PostMapping("/doLogin")
    public String doLogin( @ModelAttribute("user") User user, Model model,
                          @CookieValue(value = "setUser", defaultValue = "") String setUser,
       HttpServletResponse response, HttpServletRequest request) {
       // implement business logic
       if (user.getEmail().equals("admin@gmail.com") 
            && user.getPassword().equals("123456")) {
          if (user.getEmail() != null) {
              setUser = user.getEmail();
          }

          // create cookie and set it in response
          Cookie cookie = new Cookie("setUser", setUser);
          cookie.setMaxAge(24 * 60 * 60);
          response.addCookie(cookie);

          // get all cookies
          Cookie[] cookies = request.getCookies();
          // iterate each cookie
          for (Cookie ck : cookies) {
             // display only the cookie with the name 'setUser'
             if (!ck.getName().equals("setUser")) {
                ck.setValue("");
             }
                model.addAttribute("cookieValue", ck);
             }
             model.addAttribute("message", "Login success. Welcome!");
       } else {
          user.setEmail("");
          Cookie cookie = new Cookie("setUser", setUser);
          model.addAttribute("cookieValue", cookie);
          model.addAttribute("message", "Login failed. Try again.");
       }
       return "/login";
    }
}