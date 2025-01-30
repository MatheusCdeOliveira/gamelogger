package com.matheus.gamelogger.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegisterDTO {
	
	@Email(message = "Email inválido")
    @NotBlank(message = "O email é obrigatório")
	private String email;
	
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    @NotBlank(message = "A senha é obrigatória")
	private String password;
	
	public UserRegisterDTO() {
	}

	public UserRegisterDTO(String email, String password) {
		this.email = email;
		this.password = password;
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
	
}
