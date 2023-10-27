package com.example.microservices_users.controller;

import com.example.microservices_users.entity.Account;
import com.example.microservices_users.entity.User;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
@RequestMapping("/accounts")
public class AccountController {

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> geAccountByID(@PathVariable Long id){
        return null;
    }


    /**
     *.-.-.-.-.-.-.-.-.-.-.-.-.- ABM CUENTA -.-.-.-.-.-.-.-.-.-.-.
     */

    @PostMapping("")
    public ResponseEntity<?> createAccount(@RequestBody Account account){
        return null;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id){
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editAccount(@PathVariable Long id, @RequestBody User user){
        return null;
    }

}