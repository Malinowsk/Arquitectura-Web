package com.example.trabajoentregable3.service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NotFoundException extends RuntimeException {

    private final String message;

    public NotFoundException( String entity, Long id ){
        this.message = String.format( "La entidad %s con id %s no existe.", entity, id );
    }
}
