package com.example.microservices_scooters;

import com.example.microservices_scooters.entity.GPS;
import com.example.microservices_scooters.entity.Scooter;
import com.example.microservices_scooters.entity.Station;
import com.example.microservices_scooters.repository.ScooterRepository;
import com.example.microservices_scooters.repository.StationRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MicroservicesScootersApplication {


    @Autowired
    private ScooterRepository scooterRepo;
    @Autowired
    private StationRepository stationRepo;

    private Scooter scooter1, scooter2, scooter3, scooter4, scooter5, scooter6, scooter7, scooter8, scooter9, scooter10, scooter11, scooter12, scooter13, scooter14, scooter15;
    private Station station1, station2, station3;
    public static void main(String[] args) {
        SpringApplication.run(MicroservicesScootersApplication.class, args);
    }





    @PostConstruct
    public void setUp() {

    //Crear monopatines
    scooter1 = new Scooter();
    scooter2 = new Scooter();
    scooter3 = new Scooter();
    scooter4 = new Scooter();
    scooter5 = new Scooter();
    scooter6 = new Scooter();
    scooter7 = new Scooter();
    scooter8 = new Scooter();
    scooter9 = new Scooter();
    scooter10 = new Scooter();
    scooter11 = new Scooter();
    scooter12 = new Scooter();
    scooter13 = new Scooter();
    scooter14 = new Scooter();
    scooter15 = new Scooter();

    //Guardar monopatines bbdd
    scooterRepo.save(scooter1);
    scooterRepo.save(scooter2);
    scooterRepo.save(scooter3);
    scooterRepo.save(scooter4);
    scooterRepo.save(scooter5);
    scooterRepo.save(scooter6);
    scooterRepo.save(scooter7);
    scooterRepo.save(scooter8);
    scooterRepo.save(scooter9);
    scooterRepo.save(scooter10);
    scooterRepo.save(scooter11);
    scooterRepo.save(scooter12);
    scooterRepo.save(scooter13);
    scooterRepo.save(scooter14);
    scooterRepo.save(scooter15);




    //Se crean lista de scooters, a agregar en cada estacion
    List<Scooter> scooters1 = new ArrayList<>();
    scooters1.add(scooter1);
    scooters1.add(scooter2);
    scooters1.add(scooter3);
    scooters1.add(scooter4);
    scooters1.add(scooter5);

    List<Scooter> scooters2 = new ArrayList<>();
    scooters2.add(scooter6);
    scooters2.add(scooter7);
    scooters2.add(scooter8);
    scooters2.add(scooter9);
    scooters2.add(scooter10);

    List<Scooter> scooters3 = new ArrayList<>();
    scooters3.add(scooter11);
    scooters3.add(scooter12);
    scooters3.add(scooter13);
    scooters3.add(scooter14);
    scooters3.add(scooter15);


    //Crear estaciones
    station1 = new Station("Retiro", new GPS(-34.6099, -58.3923 ), scooters1,10);
    station2 = new Station("Constitución", new GPS(-44.5732, -58.4578), scooters2 ,9);
    station3 = new Station("Once", new GPS(-14.6221, -28.7375 ), scooters3 , 5);


    //Guardamos estaciones en ddbb
    stationRepo.save(station1);
    stationRepo.save(station2);
    stationRepo.save(station3);







    }
}
