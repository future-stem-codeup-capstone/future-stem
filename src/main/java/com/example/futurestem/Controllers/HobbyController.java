package com.example.futurestem.Controllers;

import com.example.futurestem.Models.Hobby;
import com.example.futurestem.Models.Project;
import com.example.futurestem.Repository.HobbyRepository;
import com.example.futurestem.Repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HobbyController {
    private final HobbyRepository hobbyDao;
    private final UserRepository  userDao;

    public HobbyController(HobbyRepository hobbyDao, UserRepository userDao){
        this.hobbyDao = hobbyDao;
        this.userDao = userDao;
    }

    @GetMapping("/hobbies")
    public String hobbies(Model model){
        model.addAttribute("hobbies", hobbyDao.findAll());
        return "views/Hobby/create";
    }

    @GetMapping("/views/createHobbies")    //not sure if path needs to be /views/hobby/createHobbies //
    public String hobbiesCreateForm(Model model) {
        model.addAttribute("hobbies", new Hobby());
        return "views/Hobby/create";
    }
}
