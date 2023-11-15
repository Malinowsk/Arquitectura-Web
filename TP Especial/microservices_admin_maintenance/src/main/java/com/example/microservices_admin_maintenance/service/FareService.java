package com.example.microservices_admin_maintenance.service;

import com.example.microservices_admin_maintenance.dto.*;
import com.example.microservices_admin_maintenance.entity.Fare;
import com.example.microservices_admin_maintenance.entity.ScheduledFareUpdate;
import com.example.microservices_admin_maintenance.exception.NotFoundException;
import com.example.microservices_admin_maintenance.repository.FareRepository;
import com.example.microservices_admin_maintenance.repository.ScheduledFareUpdateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class FareService {

    private final RestTemplate restTemplate;
    private final FareRepository fareRepository;
    private final ScheduledFareUpdateRepository scheduledFareUpdateRepository;

    public FareService(RestTemplate restTemplate, FareRepository fareRepository, ScheduledFareUpdateRepository scheduledFareUpdateRepository) {
        this.restTemplate = restTemplate;
        this.fareRepository = fareRepository;
        this.scheduledFareUpdateRepository = scheduledFareUpdateRepository;
    }

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

    @Transactional
    public DTOFareResponse addFare(DTOFareRequest fDTO) {
        Fare fare = new Fare(fDTO);
        Fare result = fareRepository.save(fare);
        return new DTOFareResponse(result);
    }

    /*
    @Transactional
    public DTOFareResponse updateFare(Long id, DTOFareRequest fDTO) {
        Fare fare = this.fareRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Maintenance", id));

        //Falta chequear que no vengan datos null
        fare.setName(fDTO.getName());
        fare.setCost(fDTO.getCost());
        fare.setExtended_pause_cost(fDTO.getExtended_pause_cost());

        Fare result = fareRepository.save(fare);
        return new DTOFareResponse(result);
    }

    @Transactional
    public void deleteFare(Long id) {
        this.fareRepository.delete(
                this.fareRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Maintenance", id)));
    }*/

    @Transactional
    public List<DTOScheduledFareResponse> findAllScheduledFares() {
        return this.scheduledFareUpdateRepository
                .findAll()
                .stream()
                .map(DTOScheduledFareResponse::new)
                .toList();
    }

    @Transactional
    public DTOScheduledFareResponse addScheduledFareUpdate(DTOScheduledFareRequest sfDTO, HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            ScheduledFareUpdate sfUpdate = new ScheduledFareUpdate(sfDTO);
            ScheduledFareUpdate result = this.scheduledFareUpdateRepository.save(sfUpdate);
            return new DTOScheduledFareResponse(result);
        }
        else throw new NotFoundException("error 500");
    }

    private HttpStatusCode checkPermissions(HttpHeaders headers) {
        HttpHeaders headersAux = headers;
        HttpEntity<DTORequestStatusAccount> requestEntity = new HttpEntity<>(null,headersAux);
        String user_microservice_uri = "http://localhost:8007/api/auth/admin";
        return this.restTemplate.exchange(user_microservice_uri, HttpMethod.GET, requestEntity, String.class).getStatusCode();
    }


    @Scheduled(cron = "0 0 0 * * ?")
    private void checkDateForPriceChange() {
        System.out.println(LocalDate.now());
        List<DTOScheduledFareResponse> listSFDTO = this.scheduledFareUpdateRepository
                .findScheduledFareUpdatesByDateContaining(LocalDate.now().toString())
                .stream()
                .map(DTOScheduledFareResponse::new)
                .toList();
        System.out.println(listSFDTO.size());
        for(DTOScheduledFareResponse sfDTO : listSFDTO) {
            changeScheduledFarePrice(sfDTO);
        }

    }

    private void changeScheduledFarePrice(DTOScheduledFareResponse sfDTO) {
        Fare fare = this.fareRepository.findById(sfDTO.getFare_to_update_id())
                .orElseThrow( () -> new NotFoundException("Fare", sfDTO.getFare_to_update_id()));

        fare.setCost_per_min(sfDTO.getCost_per_min());
        fare.setExtended_pause_cost(sfDTO.getExtended_pause_cost());
        this.fareRepository.save(fare);
        System.out.println("El precio ha cambiado para la tarifa con ID:" + sfDTO.getFare_to_update_id());

    }

}
