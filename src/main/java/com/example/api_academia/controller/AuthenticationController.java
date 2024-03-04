package com.example.api_academia.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_academia.Request.RequestAuthentication;
import com.example.api_academia.Request.RequestLoginResponse;
import com.example.api_academia.Request.RequestRegister;
import com.example.api_academia.infra.security.Tokenservice;
import com.example.api_academia.model.User;
import com.example.api_academia.repository.UserRepository;

import jakarta.validation.Valid;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private Tokenservice tokenService;

    @PostMapping("/login")
    public ResponseEntity<RequestLoginResponse> login(@RequestBody @Valid RequestAuthentication data) throws IllegalArgumentException, UnsupportedEncodingException {
        var usernamePassoword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassoword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok( new RequestLoginResponse(token, data.login()));
    }
    

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid RequestRegister data) {
        if(this.repository.findByLogin(data.login()) != null)
            return ResponseEntity.ok().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), data.email(), encryptedPassword, data.role());
        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }

}
