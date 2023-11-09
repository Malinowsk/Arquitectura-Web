package com.example.microservices_users.service;



import com.example.microservices_users.dto.DTORequestAccount;
import com.example.microservices_users.dto.DTOResponseAccount;
import com.example.microservices_users.entity.Account;
import com.example.microservices_users.exception.NotFoundException;
import com.example.microservices_users.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    @Autowired
    private final AccountRepository accountRepository;


    @Transactional
    public List<DTOResponseAccount> findAll(){
        return this.accountRepository.findAll().stream().map( DTOResponseAccount::new ).toList();
    }

    @Transactional
    public DTOResponseAccount findById( Long id ){
        return this.accountRepository.findById( id )
                .map( DTOResponseAccount::new )
                .orElseThrow( () -> new NotFoundException("account", id));
    }

    @Transactional
    public DTOResponseAccount save(DTORequestAccount request) {
        Account account = new Account(request);
        Account result = this.accountRepository.save(account);
        return new DTOResponseAccount(result);
    }

    @Transactional
    public void delete(Long id) {
        this.accountRepository.delete(this.accountRepository.findById(id).orElseThrow(
                () -> new NotFoundException("No se pudo eliminar la cuenta con ID:" + id)));
    }

    @Transactional
    public Account update(Long id, DTORequestAccount request) {
        Account account = this.accountRepository.findById(id).orElseThrow(
                () -> new NotFoundException("No se encontro la cuenta con ID: " + id));

        account.setMoney(request.getMoney());

        return this.accountRepository.save(account);
    }


    @Transactional
    public Account updateAccountStatus(Long id, boolean active) {
        Account account = this.accountRepository.findById(id).orElseThrow(
                () -> new NotFoundException("No se encontró la cuenta con ID: " + id));

        account.setActive(active);
        return this.accountRepository.save(account);
    }


}
