package com.example.microservices_users.entity;

import com.example.microservices_users.dto.DTORequestUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Data;
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

    @ManyToMany (fetch = FetchType.LAZY)
    private List<Account> account_list;


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
}
