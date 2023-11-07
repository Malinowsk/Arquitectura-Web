package com.example.microservices_auth.dto;

import com.example.microservices_auth.entity.Account;
import com.example.microservices_auth.entity.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;

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


    public DTOResponseUser(User u) {
        this.id = u.getId();
        this.name = u.getName();
        this.surname = u.getSurname();
        this.phone_number = u.getPhone_number();
        this.email = u.getEmail();
        this.account_list = u.getAccount_list();
    }
}
