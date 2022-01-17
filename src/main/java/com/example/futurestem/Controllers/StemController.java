package com.example.futurestem.Controllers;
import com.example.futurestem.Models.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class StemController {


    @GetMapping("/landing")
    public String showLandingPage(Model model) {
        model.addAttribute("user", new User());
        return "views/landing-page";
    }

    @GetMapping("/home")
    public String showHome() {
        return "views/home";
    }

    @GetMapping("/profile")
    public String showProfile() {
        return "views/profile";
    }

    @GetMapping("/error")
    public String showError() {return "views/error";}

    @GetMapping("/requests")
    public String showRequests() {return "views/friend-requests";}

    @GetMapping("/logout")
    public String showLogout() {return "views/logged-out";}


}
