package com.matheus.gamelogger.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheus.gamelogger.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmail(String email);
	Optional<User> findByEmail(String email);
}
