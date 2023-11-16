package com.example.microservices_users.service;

import com.example.microservices_users.dto.DTORequestUser;
import com.example.microservices_users.dto.DTOResponseUser;
import com.example.microservices_users.entity.User;
import com.example.microservices_users.exception.EnumUserException;
import com.example.microservices_users.exception.UserException;
import com.example.microservices_users.repository.AccountRepository;
import com.example.microservices_users.repository.AuthorityRepository;
import com.example.microservices_users.repository.UserRepository;
import com.example.microservices_users.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public UserService(UserRepository userRepository, RestTemplate restTemplate, AccountRepository accountRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public List<DTOResponseUser> findAll(){
        return this.userRepository.findAll().stream().map( DTOResponseUser::new ).toList();
    }

    @Transactional
    public DTOResponseUser findById( Long id ){
        return this.userRepository.findById( id )
                .map( DTOResponseUser::new )
                .orElseThrow( () -> new NotFoundException("user", id));
    }

    @Transactional
    public void delete(Long id) {
        this.userRepository.delete(this.userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("No se pudo eliminar usuario con ID:" + id)));
    }

    @Transactional
    public User update(Long id, DTORequestUser request) {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("No se encontro usuario con ID: " + id));

        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setPhone_number(request.getPhone_number());
        user.setEmail(request.getEmail());

        return this.userRepository.save(user);
    }

    @Transactional
    public String getScootersSurroundings(Long id) {
        String scooter_microservice_uri = "http://localhost:8003/api/monopatines/alrededores/" + id;
        return restTemplate.getForObject(scooter_microservice_uri, String.class);
    }

}
