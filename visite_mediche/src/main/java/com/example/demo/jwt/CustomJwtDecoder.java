package com.example.demo.jwt;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;

@Component
public class CustomJwtDecoder implements JwtDecoder{

	private static Logger log = LogManager.getLogger(CustomJwtDecoder.class);
	
	private final JwtTokenUtil jwtUtil = new JwtTokenUtil();
	
	@Override
	public Jwt decode(String token) throws JwtException {
		
		log.debug("richiamato metodo per decodificare token JWT");
		
		Jws<Claims> claimsFromToken = jwtUtil.parseClaimsFromToken(token);
		JwsHeader header = claimsFromToken.getHeader();
		Claims payload = claimsFromToken.getPayload();
		
		// controllo che il token non sia scaduto
		if (this.jwtUtil.isTokenExpired(claimsFromToken)) {
			throw new JwtException("JWT is expired [" + payload.getExpiration() + "]");
		}
		
//		TODO aggiungere ulteriori controlli sul contenuto del token
//		es. Issuer
//		es. Audience
		
		Map<String, Object> headers = new HashMap<String, Object>();		
		header.keySet().forEach(k -> headers.put(k, header.get(k)));
		
		Map<String, Object> claims = new HashMap<String, Object>();
		payload.keySet().forEach(k -> claims.put(k, payload.get(k)));
		
		return new Jwt(payload.getSubject(), payload.getIssuedAt().toInstant(), payload.getExpiration().toInstant(), headers, claims);
	}

}
