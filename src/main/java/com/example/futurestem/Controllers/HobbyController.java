package com.example.futurestem.Controllers;

import com.example.futurestem.Models.Hobby;
import com.example.futurestem.Models.Project;
import com.example.futurestem.Models.User;
import com.example.futurestem.Repository.HobbyRepository;
import com.example.futurestem.Repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HobbyController {
    private final HobbyRepository hobbyDao;
    private final UserRepository  userDao;

    public HobbyController(HobbyRepository hobbyDao, UserRepository userDao){
        this.hobbyDao = hobbyDao;
        this.userDao = userDao;
    }


        @PostMapping("/create-hobby")
        public String createHobby(@ModelAttribute Hobby hobby) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User loggedInUser = userDao.getById(user.getId());
            hobby.setUser(loggedInUser);
            hobbyDao.save(hobby);
            return "redirect:/profile";
        }


        @PostMapping("/hobby/delete/{id}")
        public String deletePost(@PathVariable Long id) {
            long deleteHobbyId = id;
            hobbyDao.deleteById(deleteHobbyId);
            return "redirect:/profile";
        }
    }
