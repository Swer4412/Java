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

//JwtTokenUtil è una classe che si occupa di gestire il token in se
//Offre funzioni per generalo, 
@Component
public class JwtTokenUtil {
    
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    
    @Value("${jwt.secret}") //Dato che non me lo prende da application.properties, devo scriverlo dentro il codice.
    private String secret = "ilTuoSecretMoltoLungoEComplesso123!@#"; 

    public String generateToken(String email, String scopes) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("scope", scopes.replaceAll(";", " "));
        return doGenerateToken(claims, email);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
            .id(UUID.randomUUID().toString())  // Identificatore unico del token
            .subject(subject)  // Identificativo dell'utente, tipicamente username o email
            .issuer("api-comuni")  // Chi ha emesso il token
            .issuedAt(new Date(System.currentTimeMillis()))  // Quando è stato emesso il token
            .setAudience("http://localhost:8080")  // A chi è destinato il token
            .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))  // Quando scadrà il token
            .claims(claims)  // Claims aggiuntivi personalizzati
            .signWith(Keys.hmacShaKeyFor(this.secret.getBytes()))  // Firma del token
            .compact();  // Costruisce il token finale
    }
    
    //FUNZIONI HELPER
    
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = parseClaimsFromToken(token);
            return !isTokenExpired(claims);
        } catch (Exception e) {
            return false;
        }
    }

    //Serve per estrarre l'email dal corpo del token, in poche parole per ottenere l'identità
    public String getEmailFromToken(String token) {
        Jws<Claims> jws = parseClaimsFromToken(token);
        return jws.getPayload().getSubject();
    }
    
    //Serve per vedere se il token é scaduto dopo 5 ore (JWT_TOKEN_VALIDITY)
    public Boolean isTokenExpired(Jws<Claims> jws) {
        final Date expiration = getExpirationDateFromToken(jws);
        return expiration.before(new Date());
    }
    
    private Date getExpirationDateFromToken(Jws<Claims> jws) {
        return jws.getPayload().getExpiration();
    }
    
    public Jws<Claims> parseClaimsFromToken(String token) {
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(this.secret.getBytes())).build().parseSignedClaims(token);
    }
}