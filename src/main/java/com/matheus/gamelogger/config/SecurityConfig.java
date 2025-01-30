package com.matheus.gamelogger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.matheus.gamelogger.security.CustomUserDetailsService;
import com.matheus.gamelogger.security.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	  private final CustomUserDetailsService customUserDetailsService;
	  private final JwtAuthenticationFilter jwtAuthenticationFilter;

	    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter) {
	        this.customUserDetailsService = customUserDetailsService;
	        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	    }
    
	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 http
         .authorizeHttpRequests(auth -> auth
        		 .requestMatchers("/h2-console/**").permitAll()
                 .requestMatchers("/auth/**").permitAll()
                 .requestMatchers("/users/register").permitAll()
                 .requestMatchers("/**").authenticated()
         )
         .csrf(csrf -> csrf
             .disable())
         .headers(headers -> headers
             .disable()  // Desabilita o cabeçalho de segurança que pode bloquear o uso de frames
         );
		 	http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	        return http.build();
	    }
	 
	 
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
	   return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
