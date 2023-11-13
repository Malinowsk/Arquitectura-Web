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
    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RestTemplate restTemplate, AccountRepository accountRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.accountRepository = accountRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
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

    @Transactional
    public String getScootersSurroundings(Long id) {
        String scooter_microservice_uri = "http://localhost:8003/api/monopatines/alrededores/" + id;
        return restTemplate.getForObject(scooter_microservice_uri, String.class);
    }

    @Transactional
    public DTOResponseUser createUser(DTORequestUser request ) {
        if( this.userRepository.existsUsersByEmailIgnoreCase( request.getEmail() ) )
            throw new UserException( EnumUserException.already_exist, String.format("Ya existe un usuario con email %s", request.getEmail() ) );
        final var accounts = this.accountRepository.findAllById( request.getAccounts() );
        if( accounts.isEmpty() )
            throw new UserException(EnumUserException.invalid_account,String.format("No se encontro ninguna cuenta con id %s", request.getAccounts().toString()));
        final var authorities = request.getAuthorities()
                .stream()
                .map( string -> this.authorityRepository.findById( string ).orElseThrow( () -> new NotFoundException("Autority", string ) ) )
                .toList();
        if( authorities.isEmpty() )
            throw new UserException( EnumUserException.invalid_authorities,
                    String.format("No se encontro ninguna autoridad con id %s", request.getAuthorities().toString() ) );
        final var user = new User( request );
        user.setAccount_list( accounts );
        user.setAuthorities( authorities );
        final var encryptedPassword = passwordEncoder.encode( request.getPassword() );
        user.setPassword( encryptedPassword );
        final var createdUser = this.userRepository.save( user );
        return new DTOResponseUser( createdUser );
    }
}
