package com.example.microservices_users;

import com.example.microservices_users.entity.Account;
import com.example.microservices_users.entity.Authority;
import com.example.microservices_users.entity.User;
import com.example.microservices_users.repository.AccountRepository;
import com.example.microservices_users.repository.AuthorityRepository;
import com.example.microservices_users.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MicroservicesUsersApplication {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepo;
    private final UserRepository userRepo;
    private final AuthorityRepository authRepo;

    public MicroservicesUsersApplication(PasswordEncoder passwordEncoder, AccountRepository accountRepo, UserRepository userRepo, AuthorityRepository authRepo) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepo = accountRepo;
        this.userRepo = userRepo;
        this.authRepo = authRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(MicroservicesUsersApplication.class, args);
    }

    @PostConstruct
    public void setUp() {
        Authority auth1= new Authority("ROLE_ADMIN");
        Authority auth2= new Authority("ROLE_USER");
        Authority auth3= new Authority("ROLE_MAINTENANCE");

        authRepo.save(auth1);
        authRepo.save(auth2);
        authRepo.save(auth3);

        //Creacion de usuarios
        List<User> users = new ArrayList<>();

        List<Authority> autorizacion1 = new ArrayList<>();
        autorizacion1.add(auth1);
        autorizacion1.add(auth2);
        autorizacion1.add(auth3);

        User u1 = new User("Martín", "Palermo", "3434224523", "martinj@gmail.com");
        u1.setPassword(passwordEncoder.encode( "123456789"));
        u1.setAuthorities(autorizacion1);
        users.add(u1);

        List<Authority> autorizacion2 = new ArrayList<>();
        autorizacion2.add(auth2);

        User u2 = new User("Lucía", "González", "3499887654", "luciag@gmail.com");
        u2.setPassword(passwordEncoder.encode( "123456780"));
        u2.setAuthorities(autorizacion2);
        users.add(u2);

        List<Authority> autorizacion3 = new ArrayList<>();
        autorizacion3.add(auth2);

        User u3 = new User("Juan", "Perez", "3544123456", "juanp@gmail.com");
        u3.setPassword(passwordEncoder.encode( "123456709"));
        u3.setAuthorities(autorizacion3);
        users.add(u3);

        List<Authority> autorizacion4 = new ArrayList<>();
        autorizacion4.add(auth2);

        User u4 = new User("Sofía", "Lopez", "3623678901", "sofial@gmail.com");
        u4.setPassword(passwordEncoder.encode( "123456089"));
        u4.setAuthorities(autorizacion4);
        users.add(u4);

        List<Authority> autorizacion5 = new ArrayList<>();
        autorizacion5.add(auth2);

        User u5 = new User("Carlos", "Bilardo", "3701122334", "carlosr@gmail.com");
        u5.setPassword(passwordEncoder.encode( "123450789"));
        u5.setAuthorities(autorizacion5);
        users.add(u5);

        List<Authority> autorizacion6 = new ArrayList<>();
        autorizacion6.add(auth2);

        User u6 = new User("Valentina", "Martínez", "3765544332", "valentinam@gmail.com");
        u6.setPassword(passwordEncoder.encode( "123406789"));
        u6.setAuthorities(autorizacion6);
        users.add(u6);

        List<Authority> autorizacion7 = new ArrayList<>();
        autorizacion7.add(auth2);

        User u7 = new User("Facundo", "Colidio", "3802345678", "facundof@gmail.com");
        u7.setPassword(passwordEncoder.encode( "123056789"));
        u7.setAuthorities(autorizacion7);
        users.add(u7);

        List<Authority> autorizacion8 = new ArrayList<>();
        autorizacion8.add(auth2);

        User u8 = new User("Camila", "Diaz", "3875678901", "camilad@gmail.com");
        u8.setPassword(passwordEncoder.encode( "120456789"));
        u8.setAuthorities(autorizacion8);
        users.add(u8);

        List<Authority> autorizacion9 = new ArrayList<>();
        autorizacion9.add(auth2);

        User u9 = new User("Matías", "Sanchez", "3951234567", "matiass@gmail.com");
        u9.setPassword(passwordEncoder.encode( "103456789"));
        u9.setAuthorities(autorizacion9);
        users.add(u9);

        List<Authority> autorizacion10 = new ArrayList<>();
        autorizacion10.add(auth2);

        User u10 = new User("Lara", "Gimenez", "3987654321", "larag@gmail.com");
        u10.setPassword(passwordEncoder.encode( "123456789"));
        u10.setAuthorities(autorizacion10);
        users.add(u10);

        //Creacion de cuentas
        List<Account> accounts1 = new ArrayList<>();

        System.out.println(users.size());

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

        //Guardamos las cuentas en la bbdd
        accountRepo.saveAll(accounts1);
        accountRepo.saveAll(accounts2);
        accountRepo.saveAll(accounts3);

        userRepo.saveAll(users);

    }
}
