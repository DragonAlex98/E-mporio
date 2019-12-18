package com.emporio.emporio.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String appURL = "http://localhost:8000";
    public static final String apiURI = "/api/v1";

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtTokenAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtTokenAuthenticationFilter(jwtTokenProvider);
    }

    // configurazione Cors per poter consumare le api restful con richieste ajax
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.setAllowedMethods(Arrays.asList("POST, PUT, GET, OPTIONS, DELETE"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
        .csrf().disable()
        // non abbiamo bisogno di una sessione
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .cors().and()
        .authorizeRequests().antMatchers(
                        // HttpMethod.GET,
                        "/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
        .antMatchers("/auth/signin").permitAll()
        .antMatchers("/auth/signup").permitAll()
        .antMatchers("/api/v1/**").permitAll()
        .antMatchers(HttpMethod.GET, "/products/**").permitAll()
        .antMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")
        .anyRequest().authenticated();

        // Filtro Custom JWT
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        http.headers().cacheControl();
    }
}