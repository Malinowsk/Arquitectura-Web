package com.example.microservices_users.entity;


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

    @ManyToMany(mappedBy = "account_list")
    private List<User> users;


}
