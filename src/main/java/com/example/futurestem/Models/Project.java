package com.example.futurestem.Models;
import javax.persistence.*;
@Entity
@Table(name = "projects")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String title;

	@Column(nullable = false)
	private String body;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}

