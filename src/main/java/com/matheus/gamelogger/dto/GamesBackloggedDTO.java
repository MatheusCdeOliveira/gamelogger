package com.matheus.gamelogger.dto;

import com.matheus.gamelogger.entities.GamesBacklogged;

public class GamesBackloggedDTO {
	  private Long id;
	  private String title;
	  private String platforms;

	  public GamesBackloggedDTO(GamesBacklogged gamesBacklogged) {
	      this.id = gamesBacklogged.getGame().getId();
	      this.title = gamesBacklogged.getGame().getTitle();
	      this.platforms = gamesBacklogged.getGame().getPlatforms();
	  }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlatforms() {
		return platforms;
	}

	public void setPlatforms(String platforms) {
		this.platforms = platforms;
	}
	
	
	  
}
