package com.matheus.gamelogger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 http
         .authorizeHttpRequests(auth -> auth
             .requestMatchers("/h2-console/**").permitAll()  // Permite o acesso ao H2 Console
             .requestMatchers("/**").permitAll()  // Permite todas as rotas sem autenticação
         )
         .csrf(csrf -> csrf
             .disable())  // Desabilita a proteção CSRF
         .headers(headers -> headers
             .disable()  // Desabilita o cabeçalho de segurança que pode bloquear o uso de frames
         );
	        return http.build();
	    }
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
