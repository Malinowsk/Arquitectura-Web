package com.example.microservices_admin_maintenance.service;

import com.example.microservices_admin_maintenance.dto.DTOFareResponse;
import com.example.microservices_admin_maintenance.exception.NotFoundException;
import com.example.microservices_admin_maintenance.repository.FareRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FareService {

    private final FareRepository fareRepository;

    @Transactional
    public DTOFareResponse findById(Long id) {
        return this.fareRepository
                .findById(String.valueOf(id))
                .map(DTOFareResponse::new)
                .orElseThrow( () -> new NotFoundException("Maintenance", id));
    }

    @Transactional
    public List<DTOFareResponse> findAll() {
        return this.fareRepository
                .findAll()
                .stream()
                .map(DTOFareResponse::new)
                .toList();
    }

}
