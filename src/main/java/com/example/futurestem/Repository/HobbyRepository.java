package com.example.futurestem.Repository;

import com.example.futurestem.Models.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {
}
