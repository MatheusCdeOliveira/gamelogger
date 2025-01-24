package com.matheus.gamelogger.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.gamelogger.dto.GamesCompletedDTO;
import com.matheus.gamelogger.entities.GamesCompleted;
import com.matheus.gamelogger.entities.User;
import com.matheus.gamelogger.repositories.GamesCompletedRepository;

import jakarta.transaction.Transactional;

@Service
public class GamesCompletedService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GamesCompletedRepository gamesCompletedRepository;
	
	@Transactional
	public List<GamesCompletedDTO> getGamesCompletedByUser (Long userId) {
		User user = userService.findById(userId);
		List<GamesCompleted> gamesCompleted = gamesCompletedRepository.findByUser(user);
		return gamesCompleted.stream().map(games -> new GamesCompletedDTO(games)).collect(Collectors.toList());
	}
}
