package com.example.microservices_users.dto;

import com.example.microservices_users.entity.User;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class DTORequestStatusAccount {
        private boolean active;
}
