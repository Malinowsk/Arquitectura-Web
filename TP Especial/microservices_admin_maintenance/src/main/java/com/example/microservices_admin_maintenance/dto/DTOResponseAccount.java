package com.example.microservices_admin_maintenance.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DTOResponseAccount {
    private Long id;
    private double money;
    private Timestamp date_of_creation;
    private boolean active;

}

