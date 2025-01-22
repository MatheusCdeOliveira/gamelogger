package com.matheus.gamelogger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheus.gamelogger.entities.GamesBacklogged;

public interface GamesBackloggedRepository extends JpaRepository<GamesBacklogged, Long> {}
