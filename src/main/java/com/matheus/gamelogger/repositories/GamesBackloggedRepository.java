package com.matheus.gamelogger.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheus.gamelogger.entities.GamesBacklogged;
import com.matheus.gamelogger.entities.User;

public interface GamesBackloggedRepository extends JpaRepository<GamesBacklogged, Long> {
	List<GamesBacklogged> findByUser(User user);
}
