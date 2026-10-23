package com.eventticket.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    public static String SECRET_KEY = "RwCy01WDTAxynO0hk2UFleofEtx4UABFlfjxbY9SmR8=";

    public String generateToken(UserDetails userDetails){

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .signWith(generateKey() , SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(generateKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T exportToken(String token, java.util.function.Function<Claims, T> function) {
        Claims claims = getClaims(token);
        return function.apply(claims);
    }

    public String getUsername(String token) {
        return exportToken(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        return exportToken(token, claims -> claims.getExpiration().before(new Date()));
    }

    private Key generateKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
