package com.example.microservices_auth.repository;

import com.example.microservices_auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmailIgnoreCase(String email);

    boolean existsUsersByEmailIgnoreCase(String email );
}
