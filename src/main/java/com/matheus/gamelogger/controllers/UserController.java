package com.matheus.gamelogger.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.gamelogger.dto.GamesBackloggedDTO;
import com.matheus.gamelogger.dto.GamesCompletedDTO;
import com.matheus.gamelogger.dto.UserRegisterDTO;
import com.matheus.gamelogger.dto.UserWithGamesDTO;
import com.matheus.gamelogger.dto.UserWithoutGamesDTO;
import com.matheus.gamelogger.dto.UsersDTO;
import com.matheus.gamelogger.entities.User;
import com.matheus.gamelogger.services.GamesBackloggedService;
import com.matheus.gamelogger.services.GamesCompletedService;
import com.matheus.gamelogger.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private GamesCompletedService gamesCompletedService;

	@Autowired
	private GamesBackloggedService gamesBackloggedService;

	@GetMapping
	public ResponseEntity<Object> findAll() {
		List<UsersDTO> users = userService.findAll();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id,
			@RequestParam(defaultValue = "false") boolean withGames) {
		if (withGames) {
			UserWithGamesDTO user = userService.findUserWithGames(id);
			return ResponseEntity.ok(user);
		} else {
			UserWithoutGamesDTO user = userService.findUserWithoutGames(id);
			return ResponseEntity.ok(user);
		}
	}

	@GetMapping("/{userId}/completed-games")
	public ResponseEntity<List<GamesCompletedDTO>> getCompletedGamesByUser(@PathVariable Long userId) {
		try {
			List<GamesCompletedDTO> gamesCompleted = gamesCompletedService.getGamesCompletedByUser(userId);
			return ResponseEntity.ok(gamesCompleted);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
		}
	}

	@GetMapping("/{userId}/backlogged-games")
	public ResponseEntity<List<GamesBackloggedDTO>> getBackloggedGamesByUser(@PathVariable Long userId) {
		try {
			List<GamesBackloggedDTO> gamesCompleted = gamesBackloggedService.getGamesBackloggedByUser(userId);
			return ResponseEntity.ok(gamesCompleted);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
		}
	}

	@PostMapping("/register")
	public ResponseEntity<Object> registerUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
		User newUser = userService.registerUser(userRegisterDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso! ID: " + newUser.getId());

	}
}
