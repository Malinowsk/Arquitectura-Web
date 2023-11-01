package com.example.microservices_scooters;

import com.example.microservices_scooters.entity.GPS;
import com.example.microservices_scooters.entity.Ride;
import com.example.microservices_scooters.entity.Scooter;
import com.example.microservices_scooters.entity.Station;
import com.example.microservices_scooters.repository.RideRepository;
import com.example.microservices_scooters.repository.ScooterRepository;
import com.example.microservices_scooters.repository.StationRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class MicroservicesScootersApplication {


    @Autowired
    private ScooterRepository scooterRepo;
    @Autowired
    private StationRepository stationRepo;
    @Autowired
    private RideRepository rideRepo;

    private Scooter scooter1, scooter2, scooter3, scooter4, scooter5, scooter6, scooter7, scooter8, scooter9, scooter10, scooter11, scooter12, scooter13, scooter14, scooter15, scooter16;
    private Station station1, station2, station3;

    private Ride ride1, ride2, ride3, ride4, ride5, ride6, ride7, ride8, ride9, ride10, ride11, ride12, ride13, ride14, ride15, ride16, ride17, ride18, ride19,ride20;
    public static void main(String[] args) {
        SpringApplication.run(MicroservicesScootersApplication.class, args);
    }





    @PostConstruct
    public void setUp() {

    //Crear monopatines
    scooter1 = new Scooter("Toyota");
    scooter1.setKmsTraveled(1000.88);
    scooter2 = new Scooter("Honda");
    scooter2.setKmsTraveled(1020.88);
    scooter3 = new Scooter("Ford");
    scooter3.setKmsTraveled(100.88);
    scooter4 = new Scooter("Chevrolet");
    scooter4.setKmsTraveled(9.88);
    scooter5 = new Scooter("Volkswagen");
    scooter5.setKmsTraveled(856.0);
    scooter6 = new Scooter("Nissan");
    scooter7 = new Scooter("BMW");
    scooter8 = new Scooter("Mercedes-Benz");
    scooter9 = new Scooter("Audi");
    scooter10 = new Scooter("Toyota");
    scooter11 = new Scooter("Ford");
    scooter12 = new Scooter("Ford");
    scooter13 = new Scooter("BMW");
    scooter14 = new Scooter("Honda");
    scooter15 = new Scooter("Toyota");
    scooter16 = new Scooter("Toyota");//monopatin sin estar en ninguna parada


    scooter1.setLocation(new GPS(-34.6099, -58.3923 ));
    scooter2.setLocation(new GPS(-34.6099, -58.3923 ));
    scooter3.setLocation(new GPS(-34.6099, -58.3923 ));
    scooter4.setLocation(new GPS(-34.6099, -58.3923 ));
    scooter5.setLocation(new GPS(-34.6099, -58.3923 ));
    scooter16.setLocation(new GPS(-34.6099, -58.3923 )); //monopatin sin estar en ninguna parada


    scooter6.setLocation(new GPS(-44.5732, -58.4578));
    scooter7.setLocation(new GPS(-44.5732, -58.4578));
    scooter8.setLocation(new GPS(-44.5732, -58.4578));
    scooter9.setLocation(new GPS(-44.5732, -58.4578));
    scooter10.setLocation(new GPS(-44.5732, -58.4578));


    scooter11.setLocation(new GPS(-14.6221, -28.7375 ));
    scooter12.setLocation(new GPS(-14.6221, -28.7375 ));
    scooter13.setLocation(new GPS(-14.6221, -28.7375 ));
    scooter14.setLocation(new GPS(-14.6221, -28.7375 ));
    scooter15.setLocation(new GPS(-14.6221, -28.7375 ));


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
    scooterRepo.save(scooter16); //monopatin sin estar en ninguna parada




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




    //Se inicializan viajes
    //Datos del microservicio users
    //Usuarios de la cuenta 1
    ride1 = new Ride(scooter1, 1, 1);
    ride2 = new Ride(scooter2, 2, 1);
    ride3 = new Ride(scooter3, 3, 1);
    ride4 = new Ride(scooter4, 4, 1);
    ride5 = new Ride(scooter5, 5, 1);

    //Usuarios de la cuenta 2
    ride6 = new Ride(scooter6, 6, 2);
    ride7 = new Ride(scooter7, 7, 2);

    //Usuarios de la cuenta 3
    ride8 = new Ride(scooter8, 8, 3);
    ride9 = new Ride(scooter9, 9, 3);
    ride10 = new Ride(scooter10, 10, 3);

    //Guardamos los viajes en la ddbb
    rideRepo.save(ride1);
    rideRepo.save(ride2);
    rideRepo.save(ride3);
    rideRepo.save(ride4);
    rideRepo.save(ride5);
    rideRepo.save(ride6);
    rideRepo.save(ride7);
    rideRepo.save(ride8);
    rideRepo.save(ride9);
    rideRepo.save(ride10);


    //Creamos viajes terminados


//Crear instancias de Ride con los valores generados
    Ride ride11 = new Ride(scooter11, 1, 1,25, 1200, station1,1L,  new Timestamp(2022 - 1900, 9 - 1, 29, 15, 10, 0, 0),  new Timestamp(2022 - 1900, 9 - 1, 29, 17, 13, 0, 0));
    Ride ride12 = new Ride(scooter2, 2, 1, 54, 1300, station2, 2L, new Timestamp(2022 - 1900, 8 - 1, 12, 15, 11, 0, 0),  new Timestamp(2022 - 1900, 8 - 1, 12, 17, 23, 0, 0));
    Ride ride13 = new Ride(scooter12, 1, 1, 72, 2320, station3,3L,  new Timestamp(2023 - 1900, 9 - 1, 14, 15, 22, 0, 0),  new Timestamp(2023 - 1900, 9 - 1, 14, 17, 33, 0, 0));
    Ride ride14 = new Ride(scooter4, 3, 1, 18, 1100, station1,1L,  new Timestamp(2023 - 1900, 8 - 1, 15, 15, 31, 0, 0),  new Timestamp(2023 - 1900, 8 - 1, 15, 17, 43, 0, 0));
    Ride ride15 = new Ride(scooter6, 4, 1, 23,  1200, station2,2L,  new Timestamp(2023 - 1900, 5 - 1, 16, 15, 3, 0, 0),  new Timestamp(2023 - 1900, 5 - 1, 16, 17, 23, 0, 0));
    Ride ride16 = new Ride(scooter8, 5, 1, 120, 3540, station3, 3L, new Timestamp(2022 - 1900, 4 - 1, 29, 15, 13, 0, 0),  new Timestamp(2022 - 1900, 4 - 1, 29, 17, 13, 0, 0));
    Ride ride17 = new Ride(scooter2, 6, 2, 11, 1000,station3, 1L, new Timestamp(2021 - 1900, 4 - 1, 20, 15, 2, 0, 0),  new Timestamp(2021 - 1900, 4 - 1, 20, 17, 32, 0, 0));
    Ride ride18 = new Ride(scooter2, 7, 2, 70, 2454, station2, 2L, new Timestamp(2021 - 1900, 3 - 1, 2, 15, 12, 0, 0),  new Timestamp(2021 - 1900, 3 - 1, 2, 17, 1, 0, 0));
    Ride ride19 = new Ride(scooter7, 8, 3, 75, 5550,station1 , 3L, new Timestamp(2022 - 1900, 2 - 1, 3, 15, 33, 0, 0),  new Timestamp(2022 - 1900, 2 - 1, 3, 17, 2, 0, 0));
    Ride ride20 = new Ride(scooter5, 9, 3, 82, 6200, station3,1L, new Timestamp(2023 - 1900, 1 - 1, 4, 15, 34, 0, 0),  new Timestamp(2023- 1900, 1 - 1, 4, 17, 33, 0, 0));


//Guardamos viajes terminados en la ddbb
    rideRepo.save(ride11);
    rideRepo.save(ride12);
    rideRepo.save(ride13);
    rideRepo.save(ride14);
    rideRepo.save(ride15);
    rideRepo.save(ride16);
    rideRepo.save(ride17);
    rideRepo.save(ride18);
    rideRepo.save(ride19);
    rideRepo.save(ride20);








    }

}

