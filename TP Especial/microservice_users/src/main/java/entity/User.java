package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class User {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String phone_number;

    @Column
    private String email;

    @ManyToMany
    private List<Account> account_list;

}
