package com.matheus.gamelogger.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "tb_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Email(message = "Email deve ser válido")
	@NotBlank(message = "Email é obrigatório")
	@Column(nullable = false, unique = true)
	private String email;
	
	@JsonIgnore
	@NotBlank(message = "Senha é obrigatória")
    @Column(nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<GamesCompleted> gamesCompleted = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<GamesBacklogged> gamesBacklogged = new ArrayList<>();
	
	public User() {
	}

	public User(Long id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<GamesCompleted> getGamesCompleted() {
		return gamesCompleted;
	}

	public void setGamesCompleted(List<GamesCompleted> gamesCompleted) {
		this.gamesCompleted = gamesCompleted;
	}

	public List<GamesBacklogged> getGamesBacklogged() {
		return gamesBacklogged;
	}

	public void setGamesBacklogged(List<GamesBacklogged> gamesBacklogged) {
		this.gamesBacklogged = gamesBacklogged;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
}
