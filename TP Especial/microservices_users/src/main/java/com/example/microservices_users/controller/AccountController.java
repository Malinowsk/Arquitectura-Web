package com.example.microservices_users.controller;

import com.example.microservices_users.dto.DTORequestAccount;
import com.example.microservices_users.dto.DTORequestStatusAccount;
import com.example.microservices_users.dto.DTOResponseAccount;
import com.example.microservices_users.entity.Account;
import com.example.microservices_users.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    ///////////////////////////////////////////////// ABM //////////////////////////////////////////////////////////////////////////

    @Operation(summary = "Obtener todas las cuentas",
            description = "Este endpoint devuelve una lista de todas las cuentas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
    })
    @GetMapping("")
    public List<DTOResponseAccount> findAll(){
        return this.accountService.findAll();
    }


    @Operation(summary = "Obtener cuenta por ID",
            description = "Este endpoint devuelve una cuenta por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountByID(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(accountService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No existe la cuenta con el ID: "+id);
        }
    }

    @Operation(summary = "Crear nueva cuenta",
            description = "Este endpoint crea una nueva cuenta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "502", description = "Bad Gateway", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody @Validated DTORequestAccount request){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(accountService.save(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("El usuario no se pudo crear.");
        }
    }

    @Operation(summary = "Eliminar cuenta por ID",
            description = "Este endpoint elimina una cuenta por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id){
        try{
            this.accountService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la cuenta con el id: " + id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo eliminar el usuario con id: " + id);
        }
    }

    @Operation(summary = "Editar cuenta por ID",
            description = "Este endpoint edita una cuenta por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })
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

    @Operation(summary = "Actualizar estado de la cuenta por ID",
            description = "Este endpoint actualiza el estado de una cuenta por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })
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