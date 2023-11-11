package com.example.microservices_users.controller;

import com.example.microservices_users.dto.DTORequestAccount;
import com.example.microservices_users.dto.DTORequestStatusAccount;
import com.example.microservices_users.dto.DTOResponseAccount;
import com.example.microservices_users.entity.Account;
import com.example.microservices_users.service.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("api/accounts")
public class AccountController {

    @Autowired
    private final AccountService accountService;

///////////////////////////////////////////////// ABM //////////////////////////////////////////////////////////////////////////

    @GetMapping("")
    public List<DTOResponseAccount> findAll(){
        return this.accountService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountByID(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(accountService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No existe la cuenta con el ID: "+id);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody @Validated DTORequestAccount request){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(accountService.save(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("El usuario no se pudo crear.");
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id){
        try{
            this.accountService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la cuenta con el id: " + id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo eliminar el usuario con id: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editAccount(@PathVariable Long id, @RequestBody @Validated DTORequestAccount request){
        try {
            Account account = accountService.update(id, request);
            DTOResponseAccount response = new DTOResponseAccount(account);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la cuenta con el ID: "+id);
        }
    }

////////////////////////////////////////////SERVICIOS-REPORTES////////////////////////////////////////////////////////////////////////

    //3.b. Como administrador quiero poder anular cuentas para inhabilitar el uso momentáneo de la misma.
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateAccountStatus(@PathVariable Long id, @RequestBody DTORequestStatusAccount request) {
        try {
            Account account = accountService.updateAccountStatus(id, request.isActive());
            DTOResponseAccount response = new DTOResponseAccount(account);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la cuenta con el ID: " + id);
        }
    }

}