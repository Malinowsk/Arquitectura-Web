package com.example.microservices_users.entity;


import com.example.microservices_users.dto.DTORequestAccount;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data  // genera automáticamente los métodos getter, setter, toString(), equals(), y hashCode()
@NoArgsConstructor
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_account")
    private Long id;
    @Column
    private Double money;
    @Column
    private Timestamp date_of_creation;
    @Column
    private boolean active = true; // 1 = true |  0 = false

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "account_list")
    @JsonBackReference
    private List<User> users;

    public Account(Double money, Timestamp date_of_creation) {
        this.money = money;
        this.date_of_creation = date_of_creation;
    }

    public Account(DTORequestAccount dto) {
        this.money = dto.getMoney();
        this.date_of_creation = dto.getDate_of_creation();
        this.users = dto.getUsers();
        this.active = dto.isActive();
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}
