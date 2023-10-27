package com.example.microservices_users.service;

import com.example.microservices_users.dto.DTORequestUser;
import com.example.microservices_users.dto.DTOResponseUser;
import com.example.microservices_users.entity.User;
import com.example.microservices_users.repository.UserRepository;
import com.example.microservices_users.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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
    public DTOResponseUser save(DTORequestUser request) {
        User user = new User(request);
        User result = this.userRepository.save(user);
        return new DTOResponseUser(result);
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




}
