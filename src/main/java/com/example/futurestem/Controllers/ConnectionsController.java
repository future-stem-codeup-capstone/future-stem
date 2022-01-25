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

//		boolean contactAlready = !connectionsDao.findByOwner_userAndAdded_user_idExists(id, currentUser.getId()).isEmpty();
		List<Connections> connectionsList = connectionsDao.findAllByOwnerUser(currentUser);
		model.addAttribute("connection", connectionsList);
		System.out.println(connectionsList.get(0).getAddedUser().getUsername());
		return "views/home";
	}
//
//
//	@PostMapping("/connections")
//	public String viewAllUsers(Model view, @PageableDefault(value=24) Pageable pageable) {
//		List<User> users = userDao.findAll();
//		view.addAttribute("connections", users.size());
//		return "redirect:/home";
//
//	}


//	@GetMapping("/profile/{username}")
//	public String showUsersProfile(Model model, @PathVariable String username) {
//		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		User userInDb = userDao.getOne(currentUser.getId());
//		model.addAttribute("user", userInDb);
//		model.addAttribute("friendsList", userInDb.getUserConnections());
//		model.addAttribute("user", userDao.findByUsername(username));
//		model.addAttribute("userName", userInDb.getUsername());
//		return "redirect:/profile";
//	}
//	@GetMapping("/users/profile/friends-request")
//	public String sendFriendRequest( @RequestParam long userId) {
//		User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		User userInDb = userDao.getOne(loggedInUser.getId());
//		User user = userDao.getOne(userId);
//		Connections connection = new Connections(loggedInUser, userInDb, user);
//		connectionsDao.save(connection);
//		return "redirect:/profile/" + user.getUsername();
//	}
////getOne old findOne old now findByID
//	@PostMapping("/profile/friends-request/delete")
//	public String delete(@RequestParam long deleteFriendId){
//		connectionsDao.deleteById(deleteFriendId);
//		return "redirect:/profile";
//	}
}
