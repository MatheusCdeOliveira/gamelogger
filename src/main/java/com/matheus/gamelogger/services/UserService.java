package com.matheus.gamelogger.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.matheus.gamelogger.dto.UserRegisterDTO;
import com.matheus.gamelogger.dto.UserWithGamesDTO;
import com.matheus.gamelogger.dto.UserWithoutGamesDTO;
import com.matheus.gamelogger.dto.UsersDTO;
import com.matheus.gamelogger.entities.User;
import com.matheus.gamelogger.exception.EmailAlreadyExistsException;
import com.matheus.gamelogger.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@Transactional
	public List<UsersDTO> findAll() {
		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			throw new UsernameNotFoundException("Nenhum usuário encontrado");
		}
		return users.stream().map(user -> new UsersDTO(user)).collect(Collectors.toList());
	}
	
	@Transactional
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
	}
	
	@Transactional
	public UserWithoutGamesDTO findUserWithoutGames(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		return new UserWithoutGamesDTO(user);
	}
	
	@Transactional
	public UserWithGamesDTO findUserWithGames(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		return new UserWithGamesDTO(user);
	}
	
	public User registerUser(UserRegisterDTO userRegisterDTO) {
		if (userRepository.existsByEmail(userRegisterDTO.getEmail())) {
			throw new EmailAlreadyExistsException("Email já cadastrado!");
		}
		User user = new User();
		user.setEmail(userRegisterDTO.getEmail());
		user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
		
		return userRepository.save(user);
	}
}
