package com.example.futurestem.Controllers;
import com.example.futurestem.Models.User;
import com.example.futurestem.Repository.ConnectionsRepository;
import com.example.futurestem.Repository.HobbyRepository;
import com.example.futurestem.Repository.ProjectRepository;
import com.example.futurestem.Repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class StemController {
    private final HobbyRepository hobbyDao;
    private final ProjectRepository projectDao;
    private final ConnectionsRepository connectionsDao;
    private final UserRepository userDao;

    public StemController(ProjectRepository projectDao, HobbyRepository hobbyDao, ConnectionsRepository connectionsDao, UserRepository userDao) {
        this.projectDao = projectDao;
        this.hobbyDao = hobbyDao;
        this.connectionsDao = connectionsDao;
        this.userDao = userDao;
    }


    @GetMapping("/")
    public String showLandingPage(Model model) {
        model.addAttribute("user", new User());
        return "views/landing-page";
    }

    @GetMapping("/info")
    public String showInfoPage(Model model) {
        model.addAttribute("user", new User());
        return "views/info-page";
    }

    @GetMapping("/home")
    public String showHome(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userDao.getById(user.getId());
        model.addAttribute("usernameNav", loggedInUser.getUsername());

        model.addAttribute("project", projectDao.findAll());
        model.addAttribute("hobby", hobbyDao.findAll());
        return "views/home";
    }


    @GetMapping("/requests")
    public String showRequests() {
        return "views/friend-requests";
    }

    @GetMapping("/map")
    public String showMap(Model model) {
//        model.addAttribute("yelpAPI", apiKey);
        return "views/map";
    }

    @GetMapping("/quizzes")
    public String showQuizzes() {
        return "views/quizzes";
    }

    @GetMapping("/stemquiz")
    public String showSTEMQuiz() {
        return "views/stemquiz";
    }
//    @GetMapping("/stemquiz")
//    public String showSTEMQuiz() {
//        return "views/stemquiz";
//    }


    @GetMapping("/aboutUs")
    public String aboutUs(Model model) {
        return "views/about-us";
    }
  
    @GetMapping("/education")
    public String showEducation (Model model){
//        model.addAttribute("yelpAPI", apiKey);
        return "views/education";

    }



}
