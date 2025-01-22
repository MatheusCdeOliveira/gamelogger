package com.matheus.gamelogger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheus.gamelogger.entities.GamesCompleted;

public interface GamesCompletedRepository extends JpaRepository<GamesCompleted, Long> {}
