package com.example.microservices_auth.dto;

import com.example.microservices_auth.entity.User;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class DTORequestAccount {
    private Double money;
    private Timestamp date_of_creation;
    private String phone_number;
    private boolean active;
    private List<User> users;
}

