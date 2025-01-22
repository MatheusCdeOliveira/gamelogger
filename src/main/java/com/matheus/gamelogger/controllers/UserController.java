package com.matheus.gamelogger.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.gamelogger.entities.User;
import com.matheus.gamelogger.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> findAll() {
		return userService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			User user = userService.findById(id);
			return ResponseEntity.ok(user);
			}
		 catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado com ID " + id);
		}
	}
}
