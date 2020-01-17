package com.emporio.emporio.unit.jwt;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import com.emporio.emporio.security.JwtTokenProvider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * JwtUnitTest
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@PropertySource(value = "classpath:application-test.properties")
public class JwtUnitTest {
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    public void whenCreateJwt_AssertIsValidAndNotExpired() {
        String token = jwtTokenProvider.createToken("utenteusername", "Titolare");
        assertTrue(jwtTokenProvider.validateToken(token));
        String refresh = jwtTokenProvider.createToken("utenteusername", "Titolare");
        assertTrue(jwtTokenProvider.isTokenNotExpired(refresh));
    }

    @Test
    public void whenCreateJwt_AssertFieldsAreCorrect() {
        String username = "utenteusername";
        String role = "Titolare";
        String token = jwtTokenProvider.createToken(username, role);
        String refresh = jwtTokenProvider.createRefreshToken(username, role);
        Jws<Claims> claims = jwtTokenProvider.getTokenClaims(token);
        Jws<Claims> refreshClaims = jwtTokenProvider.getTokenClaims(refresh);
        Date tokenIssuedAt = claims.getBody().getIssuedAt();
        Date refreshTokenIssuedAt = refreshClaims.getBody().getIssuedAt();
        Date tokenExpiration = claims.getBody().getExpiration();
        Date refreshTokenExpiration = refreshClaims.getBody().getExpiration();
        assertTrue(claims.getBody().getSubject().equalsIgnoreCase(username));
        assertTrue(claims.getBody().get("role", String.class).equalsIgnoreCase(role));
        assertTrue(tokenIssuedAt.getTime() < tokenExpiration.getTime());
        assertTrue(refreshClaims.getBody().getSubject().equalsIgnoreCase(username));
        assertTrue(refreshClaims.getBody().get("role", String.class).equalsIgnoreCase(role));
        assertTrue(refreshTokenIssuedAt.getTime() < refreshTokenExpiration.getTime());
        
        assertTrue(tokenExpiration.getTime() - tokenIssuedAt.getTime() < refreshTokenExpiration.getTime() - refreshTokenIssuedAt.getTime());
    }
}