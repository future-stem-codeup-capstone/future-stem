package com.example.futurestem.Models;

import javax.persistence.*;


@Entity
@Table(name="user_friends")
public class Friends {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn (name = "user_id")
	private User user;

}
