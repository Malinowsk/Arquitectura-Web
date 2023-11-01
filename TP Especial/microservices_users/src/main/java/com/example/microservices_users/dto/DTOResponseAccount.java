package com.example.microservices_users.dto;

import com.example.microservices_users.entity.Account;
import com.example.microservices_users.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonAutoDetect;


import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DTOResponseAccount {
    private Long id;
    private double money;
    private Timestamp date_of_creation;
    private boolean active;


    public DTOResponseAccount(Account a) {
        this.id = a.getId();
        this.money = a.getMoney();
        this.date_of_creation = a.getDate_of_creation();
        this.active = a.isActive();
    }
}

