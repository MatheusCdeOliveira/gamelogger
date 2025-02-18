package com.matheus.gamelogger.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.gamelogger.dto.AuthRequestDTO;
import com.matheus.gamelogger.dto.AuthResponseDTO;
import com.matheus.gamelogger.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody AuthRequestDTO authRequest) {
		AuthResponseDTO response = authService.login(authRequest);
		return ResponseEntity.ok(response);
	}
}
