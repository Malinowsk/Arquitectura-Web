package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Account {

    @Id
    private Long id;

    @Column
    private Double money;

    @Column
    private Timestamp date_of_creation;

    @ManyToMany(mappedBy = "user")
    private List<User> users;

}
