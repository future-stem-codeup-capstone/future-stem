package com.example.futurestem.Repository;

import com.example.futurestem.Models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	List<Project> findProjectsBy();



}
