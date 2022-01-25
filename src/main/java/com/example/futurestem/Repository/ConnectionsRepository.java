package com.example.futurestem.Repository;

import com.example.futurestem.Models.Connections;
import com.example.futurestem.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConnectionsRepository extends JpaRepository<Connections, Long> {
//	@Query(value = "SELECT * FROM  futurestem_db.user_connections WHERE user_added >= 1",  nativeQuery = true)
//	List<Connections> findConnectionsByOwner_userIs(long id);
//
//	@Query(value = "SELECT * FROM futurestem_db.user_connections  WHERE user_added >= 1 AND user_owner >= 2", nativeQuery = true)
//	List<Connections> findByOwner_userAndAdded_user_idExists(long user_added, long user_owner);
List<Connections> findAllByOwnerUser(User user);

}

