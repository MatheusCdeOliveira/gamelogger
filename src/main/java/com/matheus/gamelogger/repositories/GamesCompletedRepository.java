package com.matheus.gamelogger.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheus.gamelogger.entities.GamesCompleted;
import com.matheus.gamelogger.entities.User;

public interface GamesCompletedRepository extends JpaRepository<GamesCompleted, Long> {
		List<GamesCompleted> findByUser(User user);
}
