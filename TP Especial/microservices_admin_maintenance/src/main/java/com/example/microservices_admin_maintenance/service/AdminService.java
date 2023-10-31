package com.example.microservices_admin_maintenance.service;

import com.example.microservices_admin_maintenance.dto.DTORequestScooter;
import com.example.microservices_admin_maintenance.dto.DTOResponseScooter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AdminService {

    @Transactional
    public DTOResponseScooter saveScooter(DTORequestScooter scooter) {
        String url = "//localhost:8003/api/monopatines";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        //TODO: Terminar mañana (31/10/23), para testear antes que esto: chequear como hacer un GET y como pasar DTO a String JSON
        return null;
    }

}
