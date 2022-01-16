package com.example.futurestem.Models;

import javax.persistence.*;

@Entity
@Table(name = "hobbies")
public class Hobby {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 25)
	private String name;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Hobby() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}




}
