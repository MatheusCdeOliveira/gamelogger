package com.matheus.gamelogger.dto;

import com.matheus.gamelogger.entities.GamesCompleted;

public class GamesCompletedDTO {
	  private Long id;
	  private String title;
	  private String platforms;

	  public GamesCompletedDTO(GamesCompleted gamesCompleted) {
	      this.id = gamesCompleted.getGame().getId();
	      this.title = gamesCompleted.getGame().getTitle();
	      this.platforms = gamesCompleted.getGame().getPlatforms();
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
