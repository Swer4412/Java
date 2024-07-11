package com.example.demo.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.lang.Arrays;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {
	
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
	@Value("${jwt.secret}")
	private String secret;

//generate token for user
	public String generateToken(int id) {
		
		return doGenerateToken(id);
	}

// for retrieveing any information from token we will need the secret key
	public Jws<Claims> parseClaimsFromToken(String token) {
		
		return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(this.secret.getBytes())).build().parseSignedClaims(token);
	}

//while creating the token -
//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
//2. Sign the JWT using the HS512 algorithm and secret key.
//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
//   compaction of the JWT to a URL-safe string 
	private String doGenerateToken(int id) {
		
		return Jwts.builder()
				.id(UUID.randomUUID().toString())
				.issuer("api-comuni")
				.issuedAt(new Date(System.currentTimeMillis()))
				.setAudience("http://localhost:8080")
				.expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(Keys.hmacShaKeyFor(this.secret.getBytes()))
			.compact();
	}

	public Boolean isTokenExpired(Jws<Claims> jws) {
		final Date expiration = getExpirationDateFromToken(jws);
		return expiration.before(new Date());
	}

	private Date getExpirationDateFromToken(Jws<Claims> jws) {
		return jws.getPayload().getExpiration();
	}

}