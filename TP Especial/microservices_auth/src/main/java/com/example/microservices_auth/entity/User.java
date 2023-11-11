package com.example.microservices_auth.entity;

import com.example.microservices_auth.dto.DTORequestUser;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
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
    @JsonManagedReference
    private List<Account> account_list;

    @Getter
    @OneToMany
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

    public String getPassword() { return null; }

}
