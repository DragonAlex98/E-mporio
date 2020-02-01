package com.emporio.emporio.integration.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Date;

import com.emporio.emporio.repository.UserRepository;
import com.emporio.emporio.security.CustomUserDetailsService;
import com.emporio.emporio.security.JwtTokenProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * JwtIntegrationTest
 */
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
public class JwtIntegrationTest {
    
    @MockBean
    private UserRepository userRepository;

    @Before
    public void configure() {
        Mockito.when(userRepository.findByUsername(anyString())).thenReturn(null);
    }

    @TestConfiguration
    static class JwtTokenProviderTestContextConfiguration {
        @Autowired
        private UserRepository userRepository;

        @Bean
        public CustomUserDetailsService customUserDetailsService() {
            return new CustomUserDetailsService(userRepository);
        }

        @Bean
        public JwtTokenProvider jwtTokenProvider() {
            return new JwtTokenProvider();
        }
    }

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