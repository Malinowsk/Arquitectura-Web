package com.example.microservices_admin_maintenance.service;

import com.example.microservices_admin_maintenance.dto.DTOFareRequest;
import com.example.microservices_admin_maintenance.dto.DTOFareResponse;
import com.example.microservices_admin_maintenance.dto.DTOScheduledFareRequest;
import com.example.microservices_admin_maintenance.dto.DTOScheduledFareResponse;
import com.example.microservices_admin_maintenance.entity.Fare;
import com.example.microservices_admin_maintenance.entity.ScheduledFareUpdate;
import com.example.microservices_admin_maintenance.exception.NotFoundException;
import com.example.microservices_admin_maintenance.repository.FareRepository;
import com.example.microservices_admin_maintenance.repository.ScheduledFareUpdateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FareService {

    private final FareRepository fareRepository;
    private final ScheduledFareUpdateRepository scheduledFareUpdateRepository;

    public FareService(FareRepository fareRepository, ScheduledFareUpdateRepository scheduledFareUpdateRepository) {
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
    public DTOScheduledFareResponse addScheduledFareUpdate(DTOScheduledFareRequest sfDTO) {
        ScheduledFareUpdate sfUpdate = new ScheduledFareUpdate(sfDTO);
        ScheduledFareUpdate result = this.scheduledFareUpdateRepository.save(sfUpdate);
        return new DTOScheduledFareResponse(result);
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
