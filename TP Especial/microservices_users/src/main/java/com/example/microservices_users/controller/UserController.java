package com.example.microservices_users.controller;

import com.example.microservices_users.dto.DTOCreateUser;
import com.example.microservices_users.entity.User;
import com.example.microservices_users.service.UserService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable Long id){
        return null;
    }


    /**
     *.-.-.-.-.-.-.-.-.-.-.-.-.- ABM USUARIO -.-.-.-.-.-.-.-.-.-.-.
     */

    @PostMapping("")
    public ResponseEntity save(@RequestBody DTOCreateUser user){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("El usuario no se pudo crear.");
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(@PathVariable Long id, @RequestBody User user){
        return null;
    }

}