package com.hb.cda.thymeleafproject.controller;

import com.hb.cda.thymeleafproject.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String displayHome(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "home";
    }
}
