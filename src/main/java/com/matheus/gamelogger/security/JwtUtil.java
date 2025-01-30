package com.matheus.gamelogger.security;


import java.sql.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private static final String SECRET_KEY = "aGFzaG1hY2luZ2tleWJsb2ctY29tcGxleG5zZXZ1bGVuY2U=";
    private static final long JWT_EXPIRATION = 86400000; // 24 horas
    
    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    public String generateToken(UserDetails userDetails) {
    	String username = userDetails.getUsername();
    	
    	
    	return Jwts.builder()
    			 .subject(username)
    			 .issuedAt(new Date(System.currentTimeMillis()))
    			 .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
    			 .signWith(getSignInKey(), Jwts.SIG.HS256)
    			 .compact();
    }
    
  //  public boolean validateToken(String token, String username) {
  //  	Claims extractedUsername = extractUsername(token);
  //  	return (username.equals(extractedUsername && !isTokenExpired(token)));
  //  }

    public boolean validateToken(String token){
    	try {
    		Jwts.parser()
    		.verifyWith(getSignInKey())
    		.build()
    		.parse(token);
    		return true;	
		} catch (JwtException e) {
			// TODO: handle exception
			return false;
		}

    }

	public String extractUsername(String token) {
		return Jwts.parser()
				.verifyWith(getSignInKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}
}
