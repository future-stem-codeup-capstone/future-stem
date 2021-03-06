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

    @Column(length = 240)
    private String aboutMe;

    @Column(length = 100)
    private String profile_pic;



    @OneToMany(mappedBy = "user")
    private List<Project> userProjects;

    @OneToMany(mappedBy = "user")
    private List<Hobby> userHobbies;

    @OneToMany(mappedBy = "addedUser")
    private List<Connections> connectionsAdded;

    @OneToMany(mappedBy = "ownerUser")
    private List<Connections> connectionsId;

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

//    @OneToMany(mappedBy = "addedUser")
//    private List<Connections> connectionsAdded;

    public List<Connections> getConnectionsId() {
        return connectionsId;
    }

    public void setConnectionsId(List<Connections> connectionsId) {
        this.connectionsId = connectionsId;
    }

    public List<Connections> getConnectionsAdded() {
        return connectionsAdded;
    }

    public void setConnectionsAdded(List<Connections> connectionsAdded) {
        this.connectionsAdded = connectionsAdded;
    }

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }
    public User() {}
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }
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



    public List<Connections> getUserConnections() {
        return connectionsId;
    }
    public void setUserConnections(List<Connections> userConnections) {
        this.connectionsId = userConnections;
    }
}



