package com.example.microservices_users.controller;

import com.example.microservices_users.dto.AuthRequestDTO;
import com.example.microservices_users.dto.DTORequestUser;
import com.example.microservices_users.dto.DTOResponseUser;
import com.example.microservices_users.security.jwt.JWTFilter;
import com.example.microservices_users.security.jwt.TokenProvider;
import com.example.microservices_users.service.AuthService;
import com.example.microservices_users.service.UserService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthController(AuthService authService, UserService userService, TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.authService = authService;
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    // REGISTRARSE
    @PostMapping("/register")
    public ResponseEntity<DTOResponseUser> register(@Valid @RequestBody DTORequestUser request ){
        final var newUser = this.authService.createUser( request );
        return new ResponseEntity<>( newUser, HttpStatus.CREATED );
    }

    // INICIAR SESION
    @PostMapping("/authenticate")
    public ResponseEntity<UserController.JWTToken> authenticate(@Valid @RequestBody AuthRequestDTO request ) {
        System.out.println(request);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken( request.getEmail(), request.getPassword() );
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final var jwt = tokenProvider.createToken (authentication );
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add( JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt );
        return new ResponseEntity<>(new UserController.JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/admin")
    public Boolean checkPermissionsAdmin(){
        return this.authService.checkPermissionsAdmin();
    }

    @GetMapping("/usuario")
    public Boolean checkPermissionsUser(){
        return this.authService.checkPermissionsUser();
    }

    @GetMapping("/mantenimiento")
    public Boolean checkPermissionsMaintenance(){
        return this.authService.checkPermissionsMaintenance();
    }

}
