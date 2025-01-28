package com.matheus.gamelogger.dto;

import com.matheus.gamelogger.entities.GamesBacklogged;

public class GamesBackloggedDTO {
	  private Long id;
	  private String title;
	  private String platforms;
	  private String imgUrl;

	  public GamesBackloggedDTO(GamesBacklogged gamesBacklogged) {
	      this.id = gamesBacklogged.getGame().getId();
	      this.title = gamesBacklogged.getGame().getTitle();
	      this.platforms = gamesBacklogged.getGame().getPlatforms();
	      this.imgUrl = gamesBacklogged.getGame().getImgUrl();
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	} 
}
