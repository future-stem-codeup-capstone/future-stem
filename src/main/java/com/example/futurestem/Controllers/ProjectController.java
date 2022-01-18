package com.example.futurestem.Controllers;

import com.example.futurestem.Models.Project;
import com.example.futurestem.Repository.ProjectRepository;
import com.example.futurestem.Repository.UserRepository;
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
		project.setUser(userDao.getById(1L));
		projectDao.save(project);
		return "redirect:/views";
	}



	//	Delete Project
	@PostMapping("/project/delete/{id}")
	public String deleteProject(@PathVariable Long id){
		long deleteProjectId = id;
		projectDao.deleteById(deleteProjectId);
		return "redirect:/views";
	}

	//	Edit Project
	@GetMapping("/project/edit/{id}")
	public String editProject(@PathVariable long id, Model model) {
		Project editProject = projectDao.getById(id);
		model.addAttribute("projectToEdit", editProject);
		return "views/project/edit";
	}
	@PostMapping("/project/edit")
	public String saveProjectEdit(@RequestParam(name="projectTitle") String projectTitle,
							   @RequestParam (name="projectBody") String projectBody,
							   @RequestParam (name="projectId") long id) {
		Project projectToEdit = projectDao.getById(id);
		projectToEdit.setBody(projectBody);
		projectToEdit.setTitle(projectTitle);
		projectDao.save(projectToEdit);
		return "redirect:/views";
	}














}
