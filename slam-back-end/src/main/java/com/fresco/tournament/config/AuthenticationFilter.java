package com.fresco.tournament.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.security.Key;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fresco.tournament.models.Admin;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager authenticationManager;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            Admin adminUser = new ObjectMapper().readValue(req.getInputStream(), Admin.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(adminUser.getName(),
                            adminUser.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
	
	@Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        Date exp = new Date(System.currentTimeMillis() + JwtUtil.EXPIRATION_TIME);
        Key key = Keys.hmacShaKeyFor(JwtUtil.KEY.getBytes());
        Claims claims = Jwts.claims().setSubject(((User) auth.getPrincipal()).getUsername());
        String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, key).setExpiration(exp).compact();
        res.addHeader("token", token);
    }
}