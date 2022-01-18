package com.example.futurestem.Controllers;

import com.example.futurestem.Repository.HobbyRepository;
import com.example.futurestem.Repository.ProjectRepository;
import com.example.futurestem.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.futurestem.Models.User;

@Controller
public class UserController {
	private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;
    private final ProjectRepository projectDao;
    private final HobbyRepository hobbyDao;




    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, ProjectRepository projectDao, HobbyRepository hobbyDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.projectDao = projectDao;
        this.hobbyDao = hobbyDao;
    }



    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/landing";
    }

    @GetMapping("/profile")
    public String userProfile(Model model) {
        model.addAttribute("project", projectDao.findAll());
        model.addAttribute("hobby", hobbyDao.findAll());
        return "views/profile";
    }







}
