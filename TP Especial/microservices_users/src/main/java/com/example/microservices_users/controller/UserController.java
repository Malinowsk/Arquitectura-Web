package com.example.microservices_users.controller;

import com.example.microservices_users.dto.AuthRequestDTO;
import com.example.microservices_users.dto.DTORequestUser;
import com.example.microservices_users.dto.DTOResponseUser;
import com.example.microservices_users.entity.User;
import com.example.microservices_users.security.jwt.JWTFilter;
import com.example.microservices_users.security.jwt.TokenProvider;
import com.example.microservices_users.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public UserController(UserService userService, TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    ///////////////////////////////////////////////// ABM //////////////////////////////////////////////////////////////////////////
    @GetMapping("")
    public List<DTOResponseUser> findAll(){
        return this.userService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No existe el Usuario con el ID: "+id);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try{
            this.userService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente el usuario con el id: " + id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo eliminar el usuario con id: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(@PathVariable Long id, @RequestBody @Validated DTORequestUser request){
        try {
            User user = userService.update(id, request);
            DTOResponseUser response = new DTOResponseUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el usuario con el ID: "+id);
        }
    }
//////////////////////////////////////////// FUNCIONALIDADES ////////////////////////////////////////////////////////////////////////





////////////////////////////////////////////SERVICIOS-REPORTES////////////////////////////////////////////////////////////////////////

    //g. Como usuario quiero lun listado de los monopatines cercanos a mi zona, para poder encontrar un monopatín cerca de mi ubicación
    @Operation(summary = "Obtener un listado de monopatines cercanos a una ubicación",
            description = "Obtiene un listado de monopatines que están cerca de la ubicación especificada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("/alrededores/{id}")
    public ResponseEntity<?> getScootersSurroundings(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getScootersSurroundings(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }


    static class JWTToken {
        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }

}