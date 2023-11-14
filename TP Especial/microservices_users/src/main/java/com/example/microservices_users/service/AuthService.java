package com.example.microservices_users.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public boolean checkPermissionsAdmin() {
        return true;
    }
    public boolean checkPermissionsUser() {
        return true;
    }

    public boolean checkPermissionsMaintenance() {
        return true;
    }

}
