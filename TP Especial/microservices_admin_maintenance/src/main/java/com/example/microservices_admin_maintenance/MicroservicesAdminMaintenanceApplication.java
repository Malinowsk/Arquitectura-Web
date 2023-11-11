package com.example.microservices_admin_maintenance;

import com.example.microservices_admin_maintenance.entity.Fare;
import com.example.microservices_admin_maintenance.entity.Maintenance;
import com.example.microservices_admin_maintenance.repository.FareRepository;
import com.example.microservices_admin_maintenance.repository.MaintenanceRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;

@SpringBootApplication
public class MicroservicesAdminMaintenanceApplication {

    @Autowired
    private FareRepository fareRepo;
    @Autowired
    private MaintenanceRepository maintRepo;


    private Fare fare1, fare2, fare3;
    private Maintenance maint1, maint2, maint3, maint4, maint5, maint6, maint7, maint8, maint9, maint10;
    public static void main(String[] args) {
        SpringApplication.run(MicroservicesAdminMaintenanceApplication.class, args);
    }


    @PostConstruct
    public void setUp() {

        //Creamos tarifas
        fare1 = new Fare("Dia de Semana", 100, 200);
        fare2 = new Fare("Fin de Semana", 150, 300);
        fare3 = new Fare("Feriado", 200, 400);

        //Guardamos tarifas en bbdd
        fareRepo.save(fare1);
        fareRepo.save(fare2);
        fareRepo.save(fare3);



        //Creamos mantenimientos
        maint1 = new Maintenance(new Timestamp(2022 - 1900, 9 - 1, 20, 15, 11, 0, 0),  new Timestamp(2022 - 1900, 9 - 1, 29, 11, 41, 0, 0), 1L, 1L);
        maint2 = new Maintenance(new Timestamp(2022 - 1900, 8 - 1, 10, 10, 20, 0, 0),  new Timestamp(2022 - 1900, 9 - 1, 29, 12, 25, 0, 0), 2L, 1L);
        maint3 = new Maintenance(new Timestamp(2022 - 1900, 2 - 1, 5, 8, 33, 0, 0),  new Timestamp(2022 - 1900, 3 - 1, 1, 17, 24, 0, 0), 3L, 2L);
        maint4 = new Maintenance(new Timestamp(2023 - 1900, 3 - 1, 10, 12, 22, 0, 0),  new Timestamp(2022 - 1900, 3 - 1, 29, 8, 12, 0, 0), 4L, 2L);
        maint5 = new Maintenance(new Timestamp(2023 - 1900, 4 - 1, 8, 21, 8, 0, 0),  new Timestamp(2022 - 1900, 5 - 1, 18, 22, 41, 0, 0), 5L, 3L);
        //maint6 = new Maintenance(new Timestamp(2023 - 1900, 5 - 1, 7, 11, 1, 0, 0),  new Timestamp(2022 - 1900, 6 - 1, 29, 9, 36, 0, 0), 6L, 3L);
        //maint7 = new Maintenance(new Timestamp(2023 - 1900, 6 - 1, 21, 13, 2, 0, 0),  new Timestamp(2022 - 1900, 7 - 1, 29, 12, 32, 0, 0), 7L, 1L);
        //maint8 = new Maintenance(new Timestamp(2023 - 1900, 9 - 1, 12, 10, 15, 0, 0),  new Timestamp(2022 - 1900, 10 - 1, 29, 15, 43, 0, 0), 8L, 1L);
        //maint9 = new Maintenance(new Timestamp(2022 - 1900, 9 - 1, 15, 16, 25, 0, 0),  new Timestamp(2022 - 1900, 11 - 1, 20, 14, 22, 0, 0), 9L, 2L);
        //maint10 = new Maintenance(new Timestamp(2022 - 1900, 7 - 1, 16, 15, 32, 0, 0),  new Timestamp(2022 - 1900, 10 - 1, 29, 6, 13, 0, 0), 10L, 3L);

        //Guardamos mantenimientos en la ddbb
        maintRepo.save(maint1);
        maintRepo.save(maint2);
        maintRepo.save(maint3);
        maintRepo.save(maint4);
        maintRepo.save(maint5);
        //maintRepo.save(maint6);
        //maintRepo.save(maint7);
        //maintRepo.save(maint8);
        //maintRepo.save(maint9);
        //maintRepo.save(maint10);



    }

}
