package com.example.microservices_admin_maintenance.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DTOResponseReport {

    private Long  id;
    private String model;
    private  Long valor;

    public DTOResponseReport(Long id, String model, Double valor) {
        this.id = id;
        this.model = model;
        this.valor = valor.longValue();
    }
}
