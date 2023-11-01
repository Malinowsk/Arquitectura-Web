package com.example.microservices_scooters.dto;

import lombok.Getter;

@Getter
public class DTOResponseCharged {

    private float charged = 0;

    public DTOResponseCharged(float charged) {
            this.charged = charged;
    }
}
