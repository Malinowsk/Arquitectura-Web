package com.example.microservices_users.entity;

import com.example.microservices_users.dto.DTORequestUser;
import com.example.microservices_users.dto.DTOResponseUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Entity
@NoArgsConstructor
@Data
public class User implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_user")
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String phone_number;

    @Column
    private String email;

    @Column
    private String password;


    @ManyToMany (fetch = FetchType.LAZY)
    private List<Account> account_list;

    @ManyToMany (fetch = FetchType.LAZY)
    private List<Authority> authorities;


    public User(String name, String surname, String phone_number, String email) {
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
        this.email = email;
    }

    public User(DTORequestUser dto) {
        this.name = dto.getName();
        this.surname = dto.getSurname();
        this.phone_number = dto.getPhone_number();
        this.email = dto.getEmail();
    }
    public User(DTOResponseUser dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.surname = dto.getSurname();
        this.phone_number = dto.getPhone_number();
        this.email = dto.getEmail();
        this.account_list = dto.getAccount_list();
        this.authorities = dto.getAuthorities();
    }

}
