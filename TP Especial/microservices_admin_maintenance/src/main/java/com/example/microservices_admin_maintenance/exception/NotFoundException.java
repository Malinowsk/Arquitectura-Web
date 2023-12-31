package com.example.microservices_admin_maintenance.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private final String message;

    public NotFoundException(String entity, Long id ){
        this.message = String.format( "La entidad %s con id %s no existe.", entity, id );
    }

    public NotFoundException(String entity, String id) {
        this.message = String.format( "La entidad %s con id %s no existe.", entity, id);
    }

    public NotFoundException(String message) {
        this.message = message;
        // TODO Auto-generated constructor stub
    }
}