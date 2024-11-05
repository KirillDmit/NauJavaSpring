package ru.dmitriev.NauJavaSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage(Model model) {
        model.addAttribute("logoutSuccess", true);
        return "login";
    }
}
