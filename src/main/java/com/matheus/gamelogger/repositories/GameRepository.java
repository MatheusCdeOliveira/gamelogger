package com.matheus.gamelogger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheus.gamelogger.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long> {}
