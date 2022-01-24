package com.example.futurestem.Controllers;

import com.example.futurestem.Models.Hobby;
import com.example.futurestem.Models.Project;
import com.example.futurestem.Repository.HobbyRepository;
import com.example.futurestem.Repository.ProjectRepository;
import com.example.futurestem.Repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.futurestem.Models.User;

import java.util.List;

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
        return "redirect:/";
    }

    @PostMapping("/confirm-update")
    public String updateAccount(@ModelAttribute User user) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String hash = passwordEncoder.encode(user.getPassword());

        User editUser = new User();
        editUser.setPassword(hash);
        editUser.setId(loggedInUser.getId());
        editUser.setUsername(user.getUsername());
        editUser.setEmail(user.getEmail());


        userDao.save(editUser);
        return "redirect:/profile";
    }
    @GetMapping("/update-account")
    public String updateAccount(Model model, User user) {

        model.addAttribute("user", new User());
        user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userToEdit = userDao.getById(user.getId());
        model.addAttribute("loggedInUser", userToEdit);

        return "views/update-account";
    }


    @GetMapping("/profile")
    public String userProfile(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userDao.getById(user.getId());

        List<Hobby> userHobbies = loggedInUser.getUserHobbies();
        List<Project> userProjects = loggedInUser.getUserProjects();

//        model.addAttribute("user", loggedInUser);
        model.addAttribute("project", new Project());
        model.addAttribute("username", loggedInUser.getUsername());
        model.addAttribute("email", loggedInUser.getEmail());
        model.addAttribute("userProjects", userProjects);
        model.addAttribute("hobby", userHobbies);

        return "views/profile";
    }


    @GetMapping("/user")
    public String findAllUsers(Model model){
        model.addAttribute("user", userDao.findAll());
        return "views/home";
    }




}
