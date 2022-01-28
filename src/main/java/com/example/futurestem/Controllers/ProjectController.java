package com.example.futurestem.Controllers;

import com.example.futurestem.Models.Project;
import com.example.futurestem.Models.User;
import com.example.futurestem.Repository.ProjectRepository;
import com.example.futurestem.Repository.UserRepository;
import com.example.futurestem.Services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProjectController {
	private final UserRepository userDao;
	private final ProjectRepository projectDao;

	public ProjectController(UserRepository userDao, ProjectRepository projectDao) {
		this.userDao = userDao;
		this.projectDao = projectDao;
	}

	//Map to home page
		@GetMapping("/views")
		public String postHome(Model model){
			model.addAttribute("projects", projectDao.findAll());
			return "views/home";
		}


	//	Show on home page
	@GetMapping("/project")
	public String showProjectsHome(Model model){
		model.addAttribute("projects", projectDao.findAll());
		return "views/home";
	}


	//	Create Project
	@GetMapping("/project/create")
	public String showCreateForm(Model model) {
		model.addAttribute("project", new Project());
		return "views/project/create";
	}
	@PostMapping("/project/create")
	public String createProject(@ModelAttribute Project project) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userDao.getById(user.getId());
		project.setUser(loggedInUser);
		projectDao.save(project);
		return "redirect:/profile";
	}



	//	Delete Project
	@PostMapping("/project/delete/{id}")
	public String deleteProject(@PathVariable Long id){
		long deleteProjectId = id;
		projectDao.deleteById(deleteProjectId);
		return "redirect:/profile";
	}

	//	Edit Project
	@GetMapping("/project/edit/{id}")
	public String editProject(@PathVariable long id, Model model) {
		Project editProject = projectDao.getById(id);
		model.addAttribute("projectToEdit", editProject);
		model.addAttribute("id",editProject.getId());
		return "views/project/edit";
	}


    @PostMapping("/project/edit/{id}")
    public String saveProjectEdit(@RequestParam (name = "title") String title, @RequestParam (name = "body") String body, @PathVariable long id, @RequestParam(name = "url") String url
	) {
		Project project1 = projectDao.getById(id);
		project1.setTitle(title);
		project1.setBody(body);
		project1.setUrl(url);


		projectDao.save(project1);
        return "redirect:/profile";
    }

}
