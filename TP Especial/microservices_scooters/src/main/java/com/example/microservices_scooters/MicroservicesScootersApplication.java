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

import java.util.ArrayList;
import java.util.List;

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

    private Ride ride1, ride2, ride3, ride4, ride5, ride6, ride7, ride8, ride9, ride10;
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








    }
}
