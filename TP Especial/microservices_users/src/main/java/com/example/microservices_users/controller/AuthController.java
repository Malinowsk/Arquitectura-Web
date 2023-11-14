package com.example.microservices_users.controller;

import com.example.microservices_users.dto.DTOResponseUser;
import com.example.microservices_users.service.AuthService;
import com.example.microservices_users.service.UserService;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Data
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
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
