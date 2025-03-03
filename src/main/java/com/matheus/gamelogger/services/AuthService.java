package com.matheus.gamelogger.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.matheus.gamelogger.dto.AuthRequestDTO;
import com.matheus.gamelogger.dto.AuthResponseDTO;
import com.matheus.gamelogger.repositories.UserRepository;
import com.matheus.gamelogger.security.CustomUserDetailsService;
import com.matheus.gamelogger.security.JwtUtil;

@Service
public class AuthService {

	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	public AuthResponseDTO login(AuthRequestDTO authRequest) {

		UserDetails user = customUserDetailsService.loadUserByUsername(authRequest.getEmail());

		if (user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}

		if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
			throw new BadCredentialsException("Senha incorreta");
		}

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

		String token = jwtUtil.generateToken(user);

		return new AuthResponseDTO(token);
	}
}
