package com.example.futurestem.Controllers;

import com.example.futurestem.Models.Project;
import com.example.futurestem.Repository.ProjectRepository;
import com.example.futurestem.Repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProjectController {
	private final UserRepository userDao;
	private final ProjectRepository projectDao;

	public ProjectController(UserRepository userDao, ProjectRepository projectDao) {
		this.userDao = userDao;
		this.projectDao = projectDao;
	}

	@GetMapping("/views")
	public String postHome(Model model){
		model.addAttribute("projects", projectDao.findAll());
		return "views/landing-page";
	}

	//	Create Project
	@GetMapping("/views/createProject")
	public String showCreateForm(Model model) {
		model.addAttribute("project", new Project());
		return "views/create";
	}
//	@PostMapping("/views/createProject")
//	public String createProject(@ModelAttribute Project post) {
//		post.setUser(userDao.getById(1L));
//		projectDao.save(project);
//		return "redirect:/views";
//	}



















}
