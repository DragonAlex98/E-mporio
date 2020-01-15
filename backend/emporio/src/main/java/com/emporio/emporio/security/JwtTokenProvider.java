package com.emporio.emporio.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long tokenExpiration;

    @Value("${refresh.token.expiration}")
    private Long refreshExpiration;

    private Claims createBaseTokenClaims(String username, String role) {
        Claims claims = Jwts.claims().setSubject(username);
        Date now = new Date();
        claims.put("role", role);
        claims.setIssuedAt(now);
        return claims;
    }

    public String createToken(String username, String role) {
        Claims claims = createBaseTokenClaims(username, role);
        claims.setExpiration(new Date(claims.getIssuedAt().getTime() + tokenExpiration));
        claims.put("scope", "token");

        return Jwts.builder()
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }

    public String createRefreshToken(String username, String role) {
        Claims claims = createBaseTokenClaims(username, role);
        claims.setExpiration(new Date(claims.getIssuedAt().getTime() + refreshExpiration));
        claims.put("scope", "refresh");

        return Jwts.builder()
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public Boolean isTokenNotExpired(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return !claims.getBody().getExpiration().before(new Date());
    }

    public String refreshToken(String refreshToken) {
        String refreshedToken;
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(refreshToken);
            if (!isTokenNotExpired(refreshToken) || !claims.getBody().get("scope", String.class).equals("refresh")) {
                throw new IllegalArgumentException();
            }

            refreshedToken = createToken(claims.getBody().getSubject(), claims.getBody().get("role", String.class));
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return isTokenNotExpired(token) && claims.getBody().get("scope", String.class).equals("token");
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }
}