package com.example.microservices_users.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthRequestDTO {

    @NotNull( message = "El email es obligatorio." )
    @NotEmpty( message = "El email es obligatorio." )
    private String email;

    @NotNull( message = "La contraseña es obligatoria." )
    @NotEmpty( message = "La contraseña es obligatoria." )
    @Size(min = 6, max = 100, message = "La contraseña debe tener un minimo de 6 y un maximo de 100 caracteres.")
    private String password;

}
