package com.matheus.gamelogger.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.gamelogger.dto.GameMinDTO;
import com.matheus.gamelogger.entities.Game;
import com.matheus.gamelogger.repositories.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	public List<GameMinDTO> findAll() {
	 List<Game> result = gameRepository.findAll();
	 return result.stream().map(x -> new GameMinDTO(x)).toList();
	}
}
