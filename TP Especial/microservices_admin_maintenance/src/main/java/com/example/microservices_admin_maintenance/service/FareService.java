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
    public List<DTOFareResponse> findAll(HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            return this.fareRepository
                    .findAll()
                    .stream()
                    .map(DTOFareResponse::new)
                    .toList();
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public DTOFareResponse findById(String id,HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            return this.fareRepository
                    .findById(String.valueOf(id))
                    .map(DTOFareResponse::new)
                    .orElseThrow( () -> new NotFoundException("Maintenance", id));
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public DTOFareResponse addFare(DTOFareRequest fDTO,HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            Fare fare = new Fare(fDTO);
            Fare result = fareRepository.save(fare);
            return new DTOFareResponse(result);
        }
        else throw new NotFoundException("error 500");
    }


    @Transactional
    public DTOFareResponse updateFare(String id, DTOFareRequest fDTO,HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            Fare fare = this.fareRepository.findById(id).orElseThrow(
                    () -> new NotFoundException("Maintenance", id));

            if (fDTO.getName() != null)
                fare.setName(fDTO.getName());
            if (fDTO.getCost_per_min() != -1)
                fare.setCost_per_min(fDTO.getCost_per_min());
            if (fDTO.getExtended_pause_cost() != -1)
                fare.setExtended_pause_cost(fDTO.getExtended_pause_cost());

            Fare result = fareRepository.save(fare);
            return new DTOFareResponse(result);
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public void deleteFare(String id,HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            this.fareRepository.delete(
                    this.fareRepository.findById(id)
                            .orElseThrow(() -> new NotFoundException("Maintenance", id)));
        }
        else throw new NotFoundException("error 500");
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Transactional
    public List<DTOScheduledFareResponse> findAllScheduledFares(HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            return this.scheduledFareUpdateRepository
                    .findAll()
                    .stream()
                    .map(DTOScheduledFareResponse::new)
                    .toList();
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public ScheduledFareUpdate updateScheduledFare(String sfID, DTOScheduledFareRequest sfDTO,HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            ScheduledFareUpdate sfUpdate = this.scheduledFareUpdateRepository.findById(sfID)
                    .orElseThrow(() -> new NotFoundException("ScheduledFareUpdate", sfID));

            sfUpdate.setFare_to_update_id(sfDTO.getFare_to_update_id());
            sfUpdate.setCost_per_min(sfDTO.getCost_per_min());
            sfUpdate.setExtended_pause_cost(sfDTO.getExtended_pause_cost());
            sfUpdate.setDate(String.valueOf(sfDTO.getScheduled_date()));

            return this.scheduledFareUpdateRepository.save(sfUpdate);
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public void deleteScheduledFare(String sfID,HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            this.scheduledFareUpdateRepository.delete(
                    this.scheduledFareUpdateRepository.findById(sfID)
                            .orElseThrow(() -> new NotFoundException("ScheduledFareUpdate", sfID))
            );
        }
        else throw new NotFoundException("error 500");
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
            updateFareWithScheduledFare(sfDTO);
        }

    }

    private void updateFareWithScheduledFare(DTOScheduledFareResponse sfDTO) {
        Fare fare = this.fareRepository.findById(sfDTO.getFare_to_update_id())
                .orElseThrow( () -> new NotFoundException("Fare", sfDTO.getFare_to_update_id()));

        fare.setCost_per_min(sfDTO.getCost_per_min());
        fare.setExtended_pause_cost(sfDTO.getExtended_pause_cost());
        this.fareRepository.save(fare);
        System.out.println("El precio ha cambiado para la tarifa con ID:" + sfDTO.getFare_to_update_id());

    }



}
