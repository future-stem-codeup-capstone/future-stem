
package com.example.futurestem.Models;

import javax.persistence.*;


@Entity
@Table(name="user_connections")
public class Connections {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn (name = "user_id")
	private User user;

}

