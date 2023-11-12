package com.example.microservices_users.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public final class ErrorDTO {
    public Enum code;
    private String message;
    private LocalDateTime dateTime;

    public ErrorDTO( Enum code, String message ) {
        this.code = code;
        this.message = message;
        this.dateTime = LocalDateTime.now();
    }
}
