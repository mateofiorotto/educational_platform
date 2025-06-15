package com.mateo.plataforma_educativa.controller;

import com.mateo.plataforma_educativa.dto.AuthLoginRequestDTO;
import com.mateo.plataforma_educativa.service.UserDetailsServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Login",
            description = "Login with username and password, return access token"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logged in succesfully"),
            @ApiResponse(responseCode = "401", description = "Incorrect Login")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthLoginRequestDTO userRequest){
        return new ResponseEntity<>(this.userDetailsServiceImp.login(userRequest), HttpStatus.OK);
    }
}
