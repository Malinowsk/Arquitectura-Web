package com.example.microservices_users.dto;

import lombok.Data;
import java.io.Serializable;
@Data
public class DTOCreateUser {
    private String name;
    private String surname;
    private String phone_number;
    private String email;
}
