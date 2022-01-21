package com.example.futurestem.Controllers;

import com.example.futurestem.Models.Connections;
import com.example.futurestem.Models.User;
import com.example.futurestem.Repository.ConnectionsRepository;
import com.example.futurestem.Repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ConnectionsController {
	private UserRepository userDao;
	private ConnectionsRepository connectionsDao;

	public ConnectionsController(UserRepository userDao, ConnectionsRepository connectionsDao) {
		this.userDao = userDao;
		this.connectionsDao = connectionsDao;
	}

	@GetMapping("/users/profile/friends-request")
	public String sendFriendRequest( @RequestParam long userId) {
		User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<User> userInDb = userDao.findById(loggedInUser.getId());
		Optional<User> user = userDao.findById(userId);
		Connections connection = new Connections(loggedInUser, userInDb, user);
		connectionsDao.save(connection);
		return "redirect:/profile";
	}
//getOne old findOne old now findByID
	@PostMapping("/profile/friends-request/delete")
	public String delete(@RequestParam long deleteFriendId){
		connectionsDao.deleteById(deleteFriendId);
		return "redirect:/profile";
	}
}
