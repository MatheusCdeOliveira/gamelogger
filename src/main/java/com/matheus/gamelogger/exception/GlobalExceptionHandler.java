package com.matheus.gamelogger.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UsernameNotFoundException ex) {
		return buildErrorResponse(HttpStatus.NOT_FOUND, "Usuário não encontrado.", ex.getMessage());
	}
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<Object> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
		return buildErrorResponse(HttpStatus.CONFLICT, "Credenciais inválidas.", "E-mail já cadastrado.");
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
		return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Credenciais inválidas", "E-mail ou senha incorretos.");
	}

	@ExceptionHandler(MalformedJwtException.class)
	public ResponseEntity<Object> handleMalformedJwtException(MalformedJwtException ex) {
		return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Token inválido", ex.getMessage());
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<Object> handleExpiredJwtExceptionException(ExpiredJwtException ex) {
		return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Token expirado", "Por favor, faça o login novamente.");
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGeneralException(Exception ex) {
		return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor", ex.getMessage());
	}

	private ResponseEntity<Object> buildErrorResponse(HttpStatus status, String error, String message) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", status.value());
		body.put("error", error);
		body.put("message", message);
		return ResponseEntity.status(status).body(body);
	}
}
