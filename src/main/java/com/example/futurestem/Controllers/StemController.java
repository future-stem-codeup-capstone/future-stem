package com.example.futurestem.Controllers;
import com.example.futurestem.Models.User;
import com.example.futurestem.Repository.HobbyRepository;
import com.example.futurestem.Repository.ProjectRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class StemController {
    private final HobbyRepository hobbyDao;
    private final ProjectRepository projectDao;
    public StemController(ProjectRepository projectDao, HobbyRepository hobbyDao) {this.projectDao = projectDao; this.hobbyDao = hobbyDao;}

    @GetMapping("/landing")
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
        model.addAttribute("project", projectDao.findAll());
        model.addAttribute("hobby", hobbyDao.findAll());
        return "views/home";
    }


    @GetMapping("/error")
    public String showError() {


        return "views/error";

    }

    @GetMapping("/requests")
    public String showRequests() {return "views/friend-requests";}


//.
}
