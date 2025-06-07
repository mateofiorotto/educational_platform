package com.mateo.plataforma_educativa.controller;

import com.mateo.plataforma_educativa.dto.AuthLoginRequestDTO;
import com.mateo.plataforma_educativa.service.UserDetailsServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private UserDetailsServiceImp userDetailsServiceImp;

    public AuthController(UserDetailsServiceImp userDetailsServiceImp) {
        this.userDetailsServiceImp = userDetailsServiceImp;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthLoginRequestDTO userRequest){
        return new ResponseEntity<>(this.userDetailsServiceImp.login(userRequest), HttpStatus.OK);
    }
}
