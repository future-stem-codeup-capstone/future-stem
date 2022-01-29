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

    @GetMapping("/show-hobbies")
    public String hobbies(Model model){
        model.addAttribute("show-hobbies", hobbyDao.findAll());
        return "views/profile";
    }




//    @GetMapping("/Hobby/create")
//    public String hobbiesCreateForm(Model model) {
//        model.addAttribute("add-hobby", new Hobby());
//        return "views/profile";
//    }
    @PostMapping("/Hobby/create")
    public String createHobby(@ModelAttribute Hobby hobby, @RequestParam(name = "name") String name) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userDao.getById(user.getId());
        hobby.setUser(loggedInUser);
        hobby.setName(name);
        hobbyDao.save(hobby);
        return "redirect:/profile";
    }




    @PostMapping("/hobbies/delete/{id}")
    public String deletePost(@PathVariable long id) {
        hobbyDao.deleteById(id);
        return "redirect:/profile";
    }
}
