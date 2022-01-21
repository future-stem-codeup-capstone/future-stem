package com.example.futurestem.Controllers;

import com.example.futurestem.Repository.ConnectionsRepository;
import com.example.futurestem.Repository.UserRepository;
import org.springframework.stereotype.Controller;

@Controller
public class ConnectionsController {
	private UserRepository userDao;
	private ConnectionsRepository connectionsDao;

	public ConnectionsController(UserRepository userDao, ConnectionsRepository connectionsDao) {
		this.userDao = userDao;
		this.connectionsDao = connectionsDao;
	}




}
