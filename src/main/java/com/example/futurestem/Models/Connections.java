
package com.example.futurestem.Models;

import javax.persistence.*;
import java.util.Optional;


@Entity
@Table(name="user_connections")
public class Connections {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn (name = "user_id")
	private User user;

	public Connections() {}


	public Connections(User loggedInUser, User userInDb, User user) {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}

