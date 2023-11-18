package com.example.microservices_users.dto;

import lombok.Data;
import com.example.microservices_users.entity.User;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DTORequestAccount {
    private Double money;
    private Timestamp date_of_creation;
    private boolean active = true;
    private List<User> users = new ArrayList<>();
}

