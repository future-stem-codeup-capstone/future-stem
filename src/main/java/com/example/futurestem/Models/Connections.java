
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
	@JoinColumn (name = "user_added")
	private User addedUser;

	@ManyToOne
	@JoinColumn (name = "user_owner")
	private User ownerUser;

//	@ManyToOne
//	@JoinColumn (name = "user_added")
//	private User addedUser;

	public Connections() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getOwnerUser() {
		return ownerUser;
	}

	public void setOwnerUser(User ownerUser) {
		this.ownerUser = ownerUser;
	}

	public User getAddedUser() {
		return addedUser;
	}

	public void setAddedUser(User addedUser) {
		this.addedUser = addedUser;
	}
}

