package com.example.futurestem.Controllers;

import com.example.futurestem.Models.Hobby;
import com.example.futurestem.Models.Project;
import com.example.futurestem.Models.User;
import com.example.futurestem.Repository.HobbyRepository;
import com.example.futurestem.Repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/views/createHobbies")
    public String hobbiesCreateForm(Model model) {
        model.addAttribute("hobbies", new Hobby());
        return "views/Hobby/create";
    }

    @PostMapping("/views/createHobbies")
    public String createHobby(@ModelAttribute Hobby hobby) {

        User user = userDao.getById(1L);
        hobby.setUser(user);
        hobbyDao.save(hobby);

        return "redirect:/views/profile";
    }


    @PostMapping("/hobbies/delete/{id}")
    public String deletePost(@PathVariable long id) {
        hobbyDao.deleteById(id);

        return "redirect:/views/profile";
    }
}
