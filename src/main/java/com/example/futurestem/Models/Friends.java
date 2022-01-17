package com.example.futurestem.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users_friends")
public class Friends {
	//do i need another table of friends
//	and a table for users_friends.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany(mappedBy = "userFriends")
	private List<User> user;








}
