package com.example.futurestem.Repository;

import com.example.futurestem.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findAllByUsername(User user);

	User findByUsername(String username);
}
