package com.example.microservices_users.dto;

import lombok.Data;

import java.util.List;

@Data
public class DTORequestUser {

    private String name;
    private String surname;
    private String phone_number;
    private String email;
    private List<Long> accounts;
    private List<String> authorities;
    private String password;

}
