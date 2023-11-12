package com.example.microservices_users;

import com.example.microservices_users.config.SecurityConfiguration;
import com.example.microservices_users.entity.Account;
import com.example.microservices_users.entity.Authority;
import com.example.microservices_users.entity.User;
import com.example.microservices_users.repository.AccountRepository;
import com.example.microservices_users.repository.AuthorityRepository;
import com.example.microservices_users.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MicroservicesUsersApplication {

    @Autowired
    SecurityConfiguration securityConfiguration;

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private AuthorityRepository authRepo;

    private User user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12, user13, user14, user15;
    private Account cuenta1, cuenta2, cuenta3, cuenta4, cuenta5, cuenta6, cuenta7;

    public static void main(String[] args) {
        SpringApplication.run(MicroservicesUsersApplication.class, args);
    }

    @PostConstruct
    public void setUp() {
        Authority auth1= new Authority("ADMIN");
        Authority auth2= new Authority("USER");
        Authority auth3= new Authority("MAINTENANCE");

        authRepo.save(auth1);
        authRepo.save(auth2);
        authRepo.save(auth3);

        //Creacion de usuarios
        List<User> users = new ArrayList<>();
        users.add(new User("Martín", "Palermo", "3434224523", "martinj@gmail.com"));
        users.add(new User("Lucía", "González", "3499887654", "luciag@gmail.com"));
        users.add(new User("Juan", "Perez", "3544123456", "juanp@gmail.com"));
        users.add(new User("Sofía", "Lopez", "3623678901", "sofial@gmail.com"));
        users.add(new User("Carlos", "Bilardo", "3701122334", "carlosr@gmail.com"));
        users.add(new User("Valentina", "Martínez", "3765544332", "valentinam@gmail.com"));
        users.add(new User("Facundo", "Colidio", "3802345678", "facundof@gmail.com"));
        users.add(new User("Camila", "Diaz", "3875678901", "camilad@gmail.com"));
        users.add(new User("Matías", "Sanchez", "3951234567", "matiass@gmail.com"));
        users.add(new User("Lara", "Gimenez", "3987654321", "larag@gmail.com"));
        users.add(new User("Licha", "Lopez", "4012345678", "agustinl@gmail.com"));
        users.add(new User("Mariana", "Mendoza", "4056789012", "marianam@gmail.com"));
        users.add(new User("Lucho", "Gonzalez", "4101122334", "lucasg@gmail.com"));
        users.add(new User("Mía", "Perez", "4145566778", "miap@gmail.com"));
        users.add(new User("Felipe", "Peña", "4189001122", "feliper@gmail.com"));


        //Creacion de cuentas
        List<Account> accounts1 = new ArrayList<>();

        List<User> usersForAccount1 = new ArrayList<>();
        usersForAccount1.add(users.get(0));
        usersForAccount1.add(users.get(1));
        usersForAccount1.add(users.get(2));
        usersForAccount1.add(users.get(3));
        usersForAccount1.add(users.get(4));
        Account account1 = new Account(1000.0, Timestamp.valueOf(LocalDateTime.parse("2023-10-27T10:00:00")));
        account1.setUsers(usersForAccount1);
        accounts1.add(account1);
        users.get(0).setAccount_list(accounts1);
        users.get(1).setAccount_list(accounts1);
        users.get(2).setAccount_list(accounts1);
        users.get(3).setAccount_list(accounts1);
        users.get(4).setAccount_list(accounts1);


        List<Account> accounts2 = new ArrayList<>();

        List<User> usersForAccount2 = new ArrayList<>();
        usersForAccount2.add(users.get(5));
        usersForAccount2.add(users.get(6));
        Account account2 = new Account(1500.0, Timestamp.valueOf(LocalDateTime.parse("2023-10-28T11:00:00")));
        account2.setUsers(usersForAccount2);
        accounts2.add(account2);
        users.get(5).setAccount_list(accounts2);
        users.get(6).setAccount_list(accounts2);


        List<Account> accounts3 = new ArrayList<>();

        List<User> usersForAccount3 = new ArrayList<>();
        usersForAccount3.add(users.get(7));
        usersForAccount3.add(users.get(8));
        usersForAccount3.add(users.get(9));
        Account account3 = new Account(1200.0, Timestamp.valueOf(LocalDateTime.parse("2023-10-29T12:00:00")));
        account3.setUsers(usersForAccount3);
        accounts3.add(account3);
        users.get(7).setAccount_list(accounts3);
        users.get(8).setAccount_list(accounts3);
        users.get(9).setAccount_list(accounts3);


        List<Account> accounts4 = new ArrayList<>();

        List<User> usersForAccount4 = new ArrayList<>();
        usersForAccount4.add(users.get(10));
        usersForAccount4.add(users.get(11));
        usersForAccount4.add(users.get(12));
        Account account4 = new Account(1800.0, Timestamp.valueOf(LocalDateTime.parse("2023-10-30T14:00:00")));
        account4.setUsers(usersForAccount4);
        accounts4.add(account4);
        users.get(10).setAccount_list(accounts4);
        users.get(11).setAccount_list(accounts4);
        users.get(12).setAccount_list(accounts4);


        List<Account> accounts5 = new ArrayList<>();

        List<User> usersForAccount5 = new ArrayList<>();
        usersForAccount5.add(users.get(13));
        usersForAccount5.add(users.get(14));
        Account account5 = new Account(1400.0, Timestamp.valueOf(LocalDateTime.parse("2023-11-01T15:00:00")));
        account5.setUsers(usersForAccount5);
        accounts5.add(account5);
        users.get(13).setAccount_list(accounts5);
        users.get(14).setAccount_list(accounts5);

/*

        List<User> usersForAccount6 = new ArrayList<>();
        usersForAccount6.add(users.get(11));
        usersForAccount6.add(users.get(12));
        usersForAccount6.add(users.get(14));
        Account account6 = new Account(2000.0, Timestamp.valueOf(LocalDateTime.parse("2023-11-02T16:00:00")));
        accounts.add(account6);

        List<User> usersForAccount7 = new ArrayList<>();
        usersForAccount7.add(users.get(3));
        usersForAccount7.add(users.get(7));
        usersForAccount7.add(users.get(9));
        Account account7 = new Account(1700.0, Timestamp.valueOf(LocalDateTime.parse("2023-11-03T17:00:00")));
        accounts.add(account7);

*/


        //Guardamos las cuentas en la bbdd
        accountRepo.saveAll(accounts1);
        accountRepo.saveAll(accounts2);
        accountRepo.saveAll(accounts3);
        accountRepo.saveAll(accounts4);
        accountRepo.saveAll(accounts5);
        userRepo.saveAll(users);



    }
}
