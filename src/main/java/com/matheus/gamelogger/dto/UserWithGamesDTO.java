package com.matheus.gamelogger.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.matheus.gamelogger.entities.GamesBacklogged;
import com.matheus.gamelogger.entities.User;

public class UserWithGamesDTO {
	
	private Long id;
	private String email;
	private List<GamesCompletedDTO> gamesCompleted;
	private List<GamesBackloggedDTO> gamesBacklogged;
	
	public UserWithGamesDTO() {
	}
	
	public UserWithGamesDTO(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
	    this.gamesCompleted = user.getGamesCompleted().stream()
	             .map(GamesCompletedDTO::new)
	             .collect(Collectors.toList());
	    this.gamesBacklogged = user.getGamesBacklogged().stream()
	             .map(GamesBackloggedDTO::new)
	             .collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<GamesCompletedDTO> getGamesCompleted() {
		return gamesCompleted;
	}

	public void setGamesCompleted(List<GamesCompletedDTO> gamesCompleted) {
		this.gamesCompleted = gamesCompleted;
	}

	public List<GamesBackloggedDTO> getGamesBacklogged() {
		return gamesBacklogged;
	}

	public void setGamesBacklogged(List<GamesBackloggedDTO> gamesBacklogged) {
		this.gamesBacklogged = gamesBacklogged;
	}
	
	
}
