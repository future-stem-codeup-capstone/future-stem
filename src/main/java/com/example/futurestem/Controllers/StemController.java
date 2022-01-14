package com.example.futurestem.Controllers;
import com.example.futurestem.Repository.StemRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

@Controller
public class StemController {
    private final StemRepository stemDao;

    public StemController(StemRepository stemDao){
        this.stemDao = stemDao;
    }


    @GetMapping("landing")
    public String landingPage(Model model){
        model.addAttribute()

                return "";
    }
}
