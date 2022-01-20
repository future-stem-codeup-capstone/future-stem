package com.example.futurestem.Models;
import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Tabel(name	= "project_images")
public class ProjectImg {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String path;

	@ManyToOne
	@JoinColumn (name = "project_id")
	private Project project;

	public ProjectImg(long id, String path, Project project) {
		this.id = id;
		this.path = path;
		this.project = project;
	}
	public ProjectImg(String path, Project project) {
		this.path = path;
		this.project = project;
	}

	public ProjectImg() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
