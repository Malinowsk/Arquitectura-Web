package com.example.microservices_users.dto;

import lombok.Data;
import com.example.microservices_users.entity.User;
import java.sql.Timestamp;
import java.util.List;

@Data
public class DTORequestAccount {
    private Double money;
    private Timestamp date_of_creation;
    private String phone_number;
    private List<User> users;
}
