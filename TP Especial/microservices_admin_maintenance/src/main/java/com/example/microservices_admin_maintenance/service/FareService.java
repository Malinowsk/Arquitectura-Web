package com.example.microservices_admin_maintenance.service;

import com.example.microservices_admin_maintenance.dto.DTOFareResponse;
import com.example.microservices_admin_maintenance.dto.DTOScheduledFareRequest;
import com.example.microservices_admin_maintenance.exception.NotFoundException;
import com.example.microservices_admin_maintenance.repository.FareRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    public DTOScheduledFareRequest setScheduledFare(DTOScheduledFareRequest sfDTO) {
        this.sfDTO = sfDTO;
        return sfDTO;
    }

    private DTOScheduledFareRequest sfDTO;

    @Scheduled(cron = "0 0 0 * * ?")
    private void checkDateForPriceChange() {
        if (sfDTO != null) {
            if (sfDTO.getScheduled_date().compareTo(Timestamp.valueOf(LocalDateTime.now())) <= 0) {
                changeScheduledFarePrice();
            }
        }
    }

    private void changeScheduledFarePrice() {
        System.out.println("El precio ha cambiado");
    }

}
