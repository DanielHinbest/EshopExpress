package ca.eshopexpress.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        // Simple home page - we'll add data later
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}