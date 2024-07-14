package com.example.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginData;
import com.example.demo.entity.Utente;
import com.example.demo.jwt.JwtTokenUtil;
import com.example.demo.service.SimulazioneService;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

	private static Logger log = LogManager.getLogger(LoginController.class);
	
	//Inietto le varie dipendenze
	@Autowired
	private JwtTokenUtil jwtUtil;
	
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	@Autowired
	private SimulazioneService service;
	
	@PostMapping("/login")
	public ResponseEntity<String> generateToken(@RequestBody LoginData data) {
		
		log.debug("richiamato metodo generazione token");
		
		//Trovo l'utente in base all mail passata nel body
		Utente utente = this.service.findUserByEmail(data.getEmail());
		
		//Guardo se la password passata nel corpo matcha la password salvata nel database
		if (!this.pwdEncoder.matches(data.getPassword(), utente.getPassword())) {
			throw new BadCredentialsException("Password mismatch");
		}
		
		//Se tutto va bene genero il token
		String token = this.jwtUtil.generateToken(data.getEmail(), "user");
		
		//Ritorno il token al chiamante
		return ResponseEntity.ok(token);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Utente data) {
		
		log.debug("richiamato metodo registrazione utente");
		
		//TODO controllo che i campi siano riempiti
		
		data.setPassword(this.pwdEncoder.encode(data.getPassword()));
		
		//Salvo l'utente sul database
		this.service.saveUtente(data);
		
		//Ritorno una risposta positiva al chiamante
		return ResponseEntity.ok("");
	}
	
}