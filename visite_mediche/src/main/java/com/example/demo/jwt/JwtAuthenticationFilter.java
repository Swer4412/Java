package com.example.demo.jwt;

import com.example.demo.service.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    	//Prendo l'header con nome Authorization
        String authHeader = request.getHeader("Authorization");
        
        //Guardo se il valore di questo header inizia con Bearer
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
        	//Estraggo il token
            String token = authHeader.substring(7);
           
            //Se il token é valido
            if (jwtService.validateToken(token)) {
            	//Estraggo la email dal token decodificato
                String email = jwtService.getEmailFromToken(token);
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        
        filterChain.doFilter(request, response);
    }
}