package com.example.futurestem.Models;
import java.util.List;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
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
    private List<Project> userProjects;

    @OneToMany(mappedBy = "user")
    private List<Hobby> = userHobbies;

    @OneToMany(mappedBy = "user")
    private List<User> = userFriends;

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Project> getUserProjects() {
        return userProjects;
    }

    public void setUserProjects(List<Project> userPosts) {
        this.userProjects = userPosts;
    }


}
