package com.example.microservices_users.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NotFoundException extends RuntimeException {

    private final String message;

    public NotFoundException( String entity, Long id ){
        this.message = String.format( "La entidad %s con id %s no existe.", entity, id );
    }

    public NotFoundException( String entity, String id ){
        this.message = String.format( "%s con id %s no existe.", entity, id);
    }

}