package com.example.microservices_users.service;

import com.example.microservices_users.dto.DTORequestUser;
import com.example.microservices_users.dto.DTOResponseUser;
import com.example.microservices_users.entity.User;
import com.example.microservices_users.exception.EnumUserException;
import com.example.microservices_users.exception.NotFoundException;
import com.example.microservices_users.exception.UserException;
import com.example.microservices_users.repository.AccountRepository;
import com.example.microservices_users.repository.AuthorityRepository;
import com.example.microservices_users.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthService(AccountRepository accountRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
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


    public boolean checkPermissionsAdmin() {
        return true;
    }
    public boolean checkPermissionsUser() {
        return true;
    }

    public boolean checkPermissionsMaintenance() {
        return true;
    }



}
