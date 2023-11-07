package com.example.microservices_auth.dto;

import com.example.microservices_auth.entity.Account;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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

