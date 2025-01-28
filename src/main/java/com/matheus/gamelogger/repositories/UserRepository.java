package com.matheus.gamelogger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheus.gamelogger.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmail(String email);
}
