package com.example.futurestem.Controllers;

import com.example.futurestem.Models.Connections;
import com.example.futurestem.Models.User;
import com.example.futurestem.Repository.ConnectionsRepository;
import com.example.futurestem.Repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class ConnectionsController {
	private final ConnectionsRepository connectionsDao;
	private UserRepository userDao;

	public ConnectionsController(UserRepository userDao, ConnectionsRepository connectionsDao) {
		this.connectionsDao = connectionsDao;
		this.userDao = userDao;
	}
	@GetMapping("/connection")
	public String showConnections(Model model){
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Connections> connectionsList = connectionsDao.findAllByOwnerUser(currentUser);
		model.addAttribute("connection", connectionsList);
		model.addAttribute("allUsers", userDao.findAllByIdNotLike(currentUser.getId()));
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("connections", new Connections());
		return "views/connections";
	}


	@PostMapping("/connection/addconnection")
	public String sendFriendRequest(@RequestParam (name = "addedUser")Long userId) {
		User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User newUser = userDao.getById(userId);
		Connections newConnection = new Connections();
		newConnection.setAddedUser(newUser);
		newConnection.setOwnerUser(loggedInUser);
		connectionsDao.save(newConnection);
		return "redirect:/connection";
	}

	@PostMapping("/connection-delete/{id}")
	public String deleteConnection(@PathVariable Long id){
		long deleteConnectionId = id;
		connectionsDao.deleteById(deleteConnectionId);
		return "redirect:/connection";
	}
}
