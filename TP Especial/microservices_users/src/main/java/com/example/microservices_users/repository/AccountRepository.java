package com.example.microservices_users.repository;

import com.example.microservices_users.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
