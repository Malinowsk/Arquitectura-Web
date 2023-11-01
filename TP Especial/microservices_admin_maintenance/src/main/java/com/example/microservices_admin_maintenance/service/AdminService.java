package com.example.microservices_admin_maintenance.service;

import com.example.microservices_admin_maintenance.dto.DTORequestScooterModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AdminService {

    @Transactional
    public String createScooter(DTORequestScooterModel scooterModel) {
        String url = "http://localhost:8003/api/monopatines";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<DTORequestScooterModel> requestEntity = new HttpEntity<>(scooterModel, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
    }

}
