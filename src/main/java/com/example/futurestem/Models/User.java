package com.example.futurestem.Models;
import java.util.List;
import javax.persistence.*;

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
    private List<Hobby> userHobbies;
//    private List<User> userFriends;

//    Figure out how to do friends
//    @ManyToMany(mappedBy = "user")
//    private List<User> userFriends;



    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }

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
    public void setUserProjects(List<Project> userProjects) {
        this.userProjects = userProjects;
    }


    public List<Hobby> getUserHobbies() {
        return userHobbies;
    }
    public void setUserHobbies(List<Hobby> userHobbies) {
        this.userHobbies = userHobbies;
    }



//    public List<User> getUserFriends() {
//        return userFriends;
//    }
//    public void setUserFriends(List<User> userFriends) {
//        this.userFriends = userFriends;
//    }
}



