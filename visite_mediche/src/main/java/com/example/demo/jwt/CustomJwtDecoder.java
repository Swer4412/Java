package com.example.demo.jwt;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;

@Component
public class CustomJwtDecoder implements JwtDecoder {
    private static Logger log = LogManager.getLogger(CustomJwtDecoder.class);
    
    private final JwtTokenUtil jwtUtil;
    
    public CustomJwtDecoder(JwtTokenUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    
    @Override
    public Jwt decode(String token) throws JwtException {
        log.debug("Decoding JWT token");
        
        Jws<Claims> claimsFromToken = jwtUtil.parseClaimsFromToken(token);
        JwsHeader header = claimsFromToken.getHeader();
        Claims payload = claimsFromToken.getPayload();
        
        if (jwtUtil.isTokenExpired(claimsFromToken)) {
            throw new JwtException("JWT is expired [" + payload.getExpiration() + "]");
        }
        
        Map<String, Object> headers = new HashMap<>();        
        header.keySet().forEach(k -> headers.put(k, header.get(k)));
        
        Map<String, Object> claims = new HashMap<>();
        payload.keySet().forEach(k -> claims.put(k, payload.get(k)));
        
        return new Jwt(token, payload.getIssuedAt().toInstant(), payload.getExpiration().toInstant(), headers, claims);
    }
    
    public String getEmailFromToken(String token) {
        Jws<Claims> claimsFromToken = jwtUtil.parseClaimsFromToken(token);
        return claimsFromToken.getBody().getSubject();
    }
}