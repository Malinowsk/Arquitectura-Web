package com.example.microservices_users.controller;

import com.example.microservices_users.constant.AuthorityConstant;
import com.example.microservices_users.dto.DTORequestUser;
import com.example.microservices_users.dto.DTOResponseUser;
import com.example.microservices_users.entity.User;
import com.example.microservices_users.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private final UserService userService;

///////////////////////////////////////////////// ABM //////////////////////////////////////////////////////////////////////////
    @GetMapping("")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public List<DTOResponseUser> findAll(){
        return this.userService.findAll();
    }


    @GetMapping("/{id}")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> getUserByID(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No existe el Usuario con el ID: "+id);
        }
    }

    @PostMapping("")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> save(@RequestBody @Validated DTORequestUser request){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.save(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("El usuario no se pudo crear.");
        }

    }

    @DeleteMapping("{id}")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try{
            this.userService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente el usuario con el id: " + id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo eliminar el usuario con id: " + id);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> editUser(@PathVariable Long id, @RequestBody @Validated DTORequestUser request){
        try {
            User user = userService.update(id, request);
            DTOResponseUser response = new DTOResponseUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el usuario con el ID: "+id);
        }
    }

}