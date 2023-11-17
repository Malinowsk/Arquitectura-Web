package com.example.microservices_admin_maintenance.dto;

import com.example.microservices_admin_maintenance.entity.GPS;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class DTOResponseStation {
    private long  id;
    private String name;
    private GPS location;
    private int cantMaxSkateboards;

}
