package com.example.microservices_users.service;



import com.example.microservices_users.dto.DTORequestAccount;
import com.example.microservices_users.dto.DTOResponseAccount;
import com.example.microservices_users.entity.Account;
import com.example.microservices_users.exception.NotFoundException;
import com.example.microservices_users.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

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

        if (request.getMoney() != null)
            account.setMoney(request.getMoney());
        if (request.getDate_of_creation() != null)
            account.setDate_of_creation(request.getDate_of_creation());
        if (!java.util.Objects.equals(request.isActive(), account.isActive()))
            account.setActive(request.isActive());

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
