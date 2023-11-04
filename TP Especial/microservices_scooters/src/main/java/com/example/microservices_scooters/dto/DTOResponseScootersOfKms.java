package com.example.microservices_scooters.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DTOResponseScootersOfKms {

    private Long  id;
    private String model;
    private  Long kms;
    private  Long time;

    public DTOResponseScootersOfKms(Long id, String model, Double kms, long time) {
        this.id = id;
        this.model = model;
        this.kms = Double.valueOf(kms).longValue();
        this.time = time;
    }
}
