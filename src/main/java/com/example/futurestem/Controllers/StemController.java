package com.example.futurestem.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class StemController {


    @GetMapping("/landing")
    public String showLandingPage() {
        return "views/landing-page";
    }
}
