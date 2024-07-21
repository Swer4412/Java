package com.example.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Servizio unificato per la gestione dei JWT, inclusa la generazione, validazione e decodifica
@Component
public class JwtService implements JwtDecoder {

    @Value("${jwt.secret}")
    //Lo metto qui perchè non me lo prende da application.properties
    private String secret = "ilTuoSecretMoltoLungoEComplesso123!@#";

    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000; // 5 ore in millisecondi

    //Genera un token JWT per l'email fornita.
    public String generateToken(String email) {
    	//Ritorno il jwt costruito
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    //Valida un token JWT.
    public boolean validateToken(String token) {
        try {
        	
            // Se il parsing ha successo, il token è valido
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Qualsiasi eccezione durante il parsing indica un token non valido
            return false;
        }
    }

    //Decodifica un token JWT, da string a un oggetto Jwt di Spring Security Implementazione dell'interfaccia JwtDecoder.
    public Jwt decode(String token) throws JwtException {
        try {
        	//Converto il token stringa
            Jws<Claims> jws = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parseSignedClaims(token);
            
            //Ottengo i claims dal payload del jwt
            Claims claims = jws.getPayload();
            //Ottengo gli headers
            Map<String, Object> headers = jws.getHeader();

            //Ritorno un oggetto di tipo JWT
            return Jwt.withTokenValue(token)
                      .headers(h -> h.putAll(headers))
                      .claims(c -> c.putAll(claims))
                      .build();
        } catch (JwtException e) {
            throw new JwtException("Invalid JWT token", e);
        }
    }

    //Estrae l'email (subject) dal token JWT.
    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}