package com.example.futurestem.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {
    @PostMapping("/login")
    public String login() {
        return "views/home";
    }
}
