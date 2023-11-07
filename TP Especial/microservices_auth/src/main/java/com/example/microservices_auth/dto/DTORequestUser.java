package com.example.microservices_auth.dto;

import lombok.Data;

@Data
public class DTORequestUser {
    private String name;
    private String surname;
    private String phone_number;
    private String email;
}
