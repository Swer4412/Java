package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginData;
import com.example.demo.entity.Utente;
import com.example.demo.service.AppService;
import com.example.demo.service.JwtService;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AppService service;

    public LoginController(JwtService jwtService, PasswordEncoder passwordEncoder, AppService service) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<String> generateToken(@RequestBody LoginData data) {
    	//Prendo l'intero oggetto utente in base alla email
        Utente utente = this.service.findUtenteByEmail(data.getEmail());
        
        if (utente != null && passwordEncoder.matches(data.getPassword(), utente.getPassword())) {
            String token = jwtService.generateToken(data.getEmail()); //Si potrebbe anche gestire il ruolo me per motivi di tempo non posso farlo
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Utente data) {
    	//Cripto la password in modo da aumentare la sicurezza
        data.setPassword(this.passwordEncoder.encode(data.getPassword()));
        this.service.saveUtente(data);
        return ResponseEntity.ok("");
    }
}