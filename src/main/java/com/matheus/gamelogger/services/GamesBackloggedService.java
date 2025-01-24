package com.matheus.gamelogger.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.gamelogger.dto.GamesBackloggedDTO;
import com.matheus.gamelogger.entities.GamesBacklogged;
import com.matheus.gamelogger.entities.User;
import com.matheus.gamelogger.repositories.GamesBackloggedRepository;

import jakarta.transaction.Transactional;

@Service
public class GamesBackloggedService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GamesBackloggedRepository gamesBackloggedRepository;
	
	@Transactional
	public List<GamesBackloggedDTO> getGamesBackloggedByUser(Long userId) {
		User user = userService.findById(userId);
		List<GamesBacklogged> gamesBacklogged = gamesBackloggedRepository.findByUser(user);
		return gamesBacklogged.stream().map(games -> new GamesBackloggedDTO(games)).collect(Collectors.toList());
		
	}
}
