package com.matheus.gamelogger.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.gamelogger.entities.User;
import com.matheus.gamelogger.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			throw new RuntimeException("Nenhum usuário encontrado");
		}
		return users;
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
	}
}
