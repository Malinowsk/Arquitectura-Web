package com.example.microservices_users.service;

import com.example.microservices_users.dto.DTOCreateUser;


import com.example.microservices_users.entity.User;
import com.example.microservices_users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    @Transactional
    public User save(DTOCreateUser user) {
        User newUser = new User(user.getName(), user.getSurname(), user.getPhone_number(), user.getEmail());
        return userRepository.save(newUser);
    }



}
