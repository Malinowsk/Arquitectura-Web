package com.example.microservices_users.dto;

import com.example.microservices_users.entity.Account;
import com.example.microservices_users.entity.Authority;
import com.example.microservices_users.entity.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DTORequestUser {

    @Min(value = 0, message = "ID must be greater than 0")
    @NotNull(message = "ID cannot be null")
    private Long id;
    private String name;
    private String surname;
    private String phone_number;
    private String email;
    private List<Long> accounts;
    private List<String> authorities;
    private String password;

    public DTORequestUser(String name, String surname, String phone_number, String email, List<Long> accounts, List<String> authorities, String password) {
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
        this.email = email;
        this.accounts = accounts;
        this.authorities = authorities;
        this.password = password;
    }

    public DTORequestUser(User dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.surname = dto.getSurname();
        this.phone_number = dto.getPhone_number();
        this.email = dto.getEmail();
        this.accounts = new ArrayList<>();
        for (Account acc : dto.getAccount_list()) {
            this.accounts.add(acc.getId());
        }
        this.authorities = new ArrayList<>();
        for (Authority auth : dto.getAuthorities()) {
            this.authorities.add(auth.getName());
        }
        this.password = dto.getPassword();
    }
}
