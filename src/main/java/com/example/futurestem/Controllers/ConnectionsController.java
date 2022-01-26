package com.example.futurestem.Controllers;

import com.example.futurestem.Models.Connections;
import com.example.futurestem.Models.User;
import com.example.futurestem.Repository.ConnectionsRepository;
import com.example.futurestem.Repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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
		model.addAttribute("allUsers", userDao.findAll());
		return "views/connections";
	}


//	@GetMapping("/connection/{username}")
//	public String showUsersProfile(Model model, @PathVariable String username) {
//		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		User userInDb = userDao.getOne(currentUser.getId());
//		model.addAttribute("user", userInDb);
//		model.addAttribute("friendsList", userInDb.getUserConnections());
//		model.addAttribute("user", userDao.findByUsername(username));
//		model.addAttribute("userName", userInDb.getUsername());
//		return "views/connections";
//	}
	@GetMapping("/connection/friends-request")
	public String sendFriendRequest( @RequestParam long userId) {
		User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userInDb = userDao.getById(loggedInUser.getId());
		User user = userDao.getById(userId);
		Connections connection = new Connections(user, userInDb);
		connectionsDao.save(connection);
		return "views/connections" + user.getUsername();
	}
////getOne old findOne old now findByID
//	@PostMapping("/profile/friends-request/delete")
//	public String delete(@RequestParam long deleteFriendId){
//		connectionsDao.deleteById(deleteFriendId);
//		return "redirect:/profile";
//	}
}
