package hr.lknezevic.taskflow.taskflowserver.service.impl;

import hr.lknezevic.taskflow.taskflowserver.config.JwtConfig;
import hr.lknezevic.taskflow.taskflowserver.enums.UserRole;
import hr.lknezevic.taskflow.taskflowserver.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final JwtConfig jwtConfig;

    public JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(String username, UserRole role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getJwtExpiration()))
                .signWith(getSignKey()) // BEZ SignatureAlgorithm
                .compact();
    }

    public UserRole extractUserRole(String token) {
        return extractClaim(token, claims -> claims.get("role", UserRole.class));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()  // Koristi parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token, User user) {
        final String username = extractUsername(token);
        return (username.equals(user.getUsername()) && !extractExpiration(token).before(new Date()));
    }

    private Key getSignKey() {
        byte[] keyBytes = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes()).getEncoded();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
}
