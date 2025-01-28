package com.matheus.gamelogger.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matheus.gamelogger.entities.GamesCompleted;

public class GamesCompletedDTO {
	  private Long id;
	  private String title;
	  private String platforms;
	  private String imgUrl;
	  
	  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	  private LocalDate completionDate;
	  private Double userRating;

	  public GamesCompletedDTO(GamesCompleted gamesCompleted) {
	      this.id = gamesCompleted.getGame().getId();
	      this.title = gamesCompleted.getGame().getTitle();
	      this.platforms = gamesCompleted.getGame().getPlatforms();
	      this.imgUrl = gamesCompleted.getGame().getImgUrl();
	      this.completionDate = gamesCompleted.getCompletionDate();
	      this.userRating = gamesCompleted.getUserRating();
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

	public LocalDate getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}

	public Double getUserRating() {
		return userRating;
	}

	public void setUserRating(Double userRating) {
		this.userRating = userRating;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	} 
}
