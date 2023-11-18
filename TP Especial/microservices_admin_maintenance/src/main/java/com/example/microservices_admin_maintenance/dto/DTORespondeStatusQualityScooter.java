package com.example.microservices_admin_maintenance.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DTORespondeStatusQualityScooter {

    private long quantity_in_use;
    private long quantity_maintenance;
}
