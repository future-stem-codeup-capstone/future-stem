package com.example.futurestem.Models;

import javax.persistence.*;

@Entity
@Table(name = "hobbies")
public class Hobby {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column

}
