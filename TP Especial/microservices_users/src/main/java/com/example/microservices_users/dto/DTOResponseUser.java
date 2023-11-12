package com.example.microservices_users.dto;

import com.example.microservices_users.entity.Account;
import com.example.microservices_users.entity.Authority;
import com.example.microservices_users.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonAutoDetect;


import java.util.List;

@Data
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DTOResponseUser {
    private Long id;
    private String name;
    private String surname;
    private String phone_number;
    private String email;
    private List<Account> account_list;
    private List<Authority> authorities;


    public DTOResponseUser(User u) {
        this.id = u.getId();
        this.name = u.getName();
        this.surname = u.getSurname();
        this.phone_number = u.getPhone_number();
        this.email = u.getEmail();
        this.account_list = u.getAccount_list();
        this.authorities = u.getAuthorities();
    }
}
