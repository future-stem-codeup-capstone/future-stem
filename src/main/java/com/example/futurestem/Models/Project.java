package com.example.futurestem.Models;
import javax.persistence.*;
import java.util.List;

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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	private List<ProjectImg> projectImgs;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ProjectImg> getProjectImgs() {
		return projectImgs;
	}

	public void setProjectImgs(List<ProjectImg> projectImgs) {
		this.projectImgs = projectImgs;
	}

}

