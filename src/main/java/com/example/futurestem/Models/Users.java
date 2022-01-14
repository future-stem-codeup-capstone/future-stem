package com.example.futurestem.Models;

import java.util.List;

@Entity
@Table(name = "blog_users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 240, unique = true)
    private String username;

    @Column(nullable = false, length = 240, unique = true)
    private String email;

    @Column(nullable = false, length = 240)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Post> userPosts;
}
