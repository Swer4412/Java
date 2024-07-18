package com.example.demo.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {
    
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    
    @Value("${jwt.secret}")
    private String secret = "ilTuoSecretMoltoLungoEComplesso123!@#";

    public String generateToken(String email, String scopes) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("scope", scopes.replaceAll(";", " "));
        return doGenerateToken(claims, email);
    }

    public Jws<Claims> parseClaimsFromToken(String token) {
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(this.secret.getBytes())).build().parseSignedClaims(token);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .subject(subject)
                .issuer("api-comuni")
                .issuedAt(new Date(System.currentTimeMillis()))
                .setAudience("http://localhost:8080")
                .claims(claims)
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

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = parseClaimsFromToken(token);
            return !isTokenExpired(claims);
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        Jws<Claims> claims = parseClaimsFromToken(token);
        return claims.getBody().getSubject();
    }
}