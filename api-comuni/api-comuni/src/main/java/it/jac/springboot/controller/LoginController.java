package it.jac.springboot.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.springboot.dto.LoginData;
import it.jac.springboot.entity.Utente;
import it.jac.springboot.jwt.JwtTokenUtil;
import it.jac.springboot.service.UtenteService;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

	private static Logger log = LogManager.getLogger(LoginController.class);
	
	@Autowired
	private JwtTokenUtil jwtUtil;
	
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	@Autowired
	private UtenteService service;
	
	@PostMapping("/login")
	public ResponseEntity<String> generateToken(@RequestBody LoginData data) {
		
		log.debug("richiamato metodo generazione token");
		
		Utente entity = this.service.findByUsername(data.getUsername());
		
//		check password
		if (!this.pwdEncoder.matches(data.getPassword(), entity.getPassword())) {
			throw new BadCredentialsException("Password mismatch");
		}
		
		return ResponseEntity.ok(this.jwtUtil.generateToken(data.getUsername(), entity.getRoles()));
	}

	@GetMapping("/test")
	public ResponseEntity<String> test() {

		return ResponseEntity.ok("ok");
	}
	
}
