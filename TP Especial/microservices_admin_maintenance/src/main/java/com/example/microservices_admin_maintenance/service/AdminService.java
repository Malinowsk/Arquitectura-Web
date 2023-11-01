package com.example.microservices_admin_maintenance.service;

import com.example.microservices_admin_maintenance.dto.DTORequestScooter;
import com.example.microservices_admin_maintenance.dto.DTORequestScooterModel;
import com.example.microservices_admin_maintenance.dto.DTOResponseScooter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AdminService {

    @Transactional
    public ResponseEntity<String> createScooter(DTORequestScooterModel scooterModel) {
        String url = "http://localhost:8003/api/monopatines";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<DTORequestScooterModel> requestEntity = new HttpEntity<>(scooterModel, headers);
        System.out.print(scooterModel);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    }

}
