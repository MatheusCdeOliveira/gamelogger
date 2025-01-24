package com.matheus.gamelogger.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.gamelogger.dto.UserWithGamesDTO;
import com.matheus.gamelogger.dto.UserWithoutGamesDTO;
import com.matheus.gamelogger.entities.User;
import com.matheus.gamelogger.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public List<User> findAll() {
		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			throw new RuntimeException("Nenhum usuário encontrado");
		}
		return users;
	}
	
	@Transactional
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
	}
	
	@Transactional
	public UserWithoutGamesDTO findUserWithoutGames(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		return new UserWithoutGamesDTO(user);
	}
	
	@Transactional
	public UserWithGamesDTO findUserWithGames(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		return new UserWithGamesDTO(user);
	}
}
