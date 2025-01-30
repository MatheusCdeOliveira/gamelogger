package com.matheus.gamelogger.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
	
	private UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Ignora a rota /users/register e /h2-console (onde não precisa de token)
        if (request.getRequestURI().startsWith("/users/register") || request.getRequestURI().startsWith("/h2-console")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Verifica se o cabeçalho Authorization está presente
        String header = request.getHeader("Authorization");

        // Se o cabeçalho não contiver "Bearer", ignora a requisição
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extrai o token do cabeçalho
        String token = header.substring(7);  // Remove "Bearer " do início

        try {
            // Verifica a validade do token
            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.extractUsername(token);

                // Carregar as authorities (se necessário)
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Se o token for válido, cria a autenticação com as authorities
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (ExpiredJwtException | MalformedJwtException e) {
            // Se o token for inválido ou expirado, não faz a autenticação
            SecurityContextHolder.clearContext();
        }

        // Continua a execução da requisição
        filterChain.doFilter(request, response);
    }

}
