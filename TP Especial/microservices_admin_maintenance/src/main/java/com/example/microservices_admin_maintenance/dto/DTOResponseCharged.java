package com.example.microservices_admin_maintenance.dto;

import lombok.Getter;

@Getter
public class DTOResponseCharged {

    private float charged = 0;

    public DTOResponseCharged(float charged) {
            this.charged = charged;
    }
}
