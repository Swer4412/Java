package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginData;
import com.example.demo.entity.Utente;
import com.example.demo.jwt.JwtTokenUtil;
import com.example.demo.service.AppService;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final AppService service;

    public LoginController(JwtTokenUtil jwtTokenUtil, PasswordEncoder passwordEncoder, AppService service) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<String> generateToken(@RequestBody LoginData data) {
        Utente utente = this.service.findUserByEmail(data.getEmail());
        
        if (utente != null && passwordEncoder.matches(data.getPassword(), utente.getPassword())) {
            String token = jwtTokenUtil.generateToken(data.getEmail(), utente.getRuolo());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Utente data) {
        data.setPassword(this.passwordEncoder.encode(data.getPassword()));
        this.service.saveUtente(data);
        return ResponseEntity.ok("User registered successfully");
    }
}