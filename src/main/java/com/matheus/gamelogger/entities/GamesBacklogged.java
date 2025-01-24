package com.matheus.gamelogger.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "tb_games_backlogged")
public class GamesBacklogged {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@NotNull(message = "O usuário é obrigatório")
	@JsonBackReference
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "game_id", nullable = false)
	@NotNull(message = "O jogo é obrigatório")
	@JsonBackReference
	private Game game;
	
	public GamesBacklogged() {
	}

	public GamesBacklogged(Long id, User user, Game game) {
		this.id = id;
		this.user = user;
		this.game = game;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GamesBacklogged other = (GamesBacklogged) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
