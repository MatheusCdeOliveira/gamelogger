package com.matheus.gamelogger.dto;

import com.matheus.gamelogger.entities.User;

public class UserWithoutGamesDTO {
	
	private Long id;
	private String email;
	
	public UserWithoutGamesDTO() {
	}
	
	public UserWithoutGamesDTO(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
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
}
