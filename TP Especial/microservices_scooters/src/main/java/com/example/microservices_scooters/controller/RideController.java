package com.example.microservices_scooters.controller;

import com.example.microservices_scooters.dto.*;
import com.example.microservices_scooters.entity.Ride;
import com.example.microservices_scooters.entity.Scooter;
import com.example.microservices_scooters.service.RideService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/viajes")
@RequiredArgsConstructor
public class RideController {

    @Autowired
    private RideService rideService;

///////////////////////////////////////////////// ABM //////////////////////////////////////////////////////////////////////////

    @Operation(summary = "Obtener una lista de viajes",
            description = "Obtiene una lista de todos los viajes disponibles en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOResponseRide.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("")
    public List<DTOResponseRide> findAll(){
        return this.rideService.findAll();
    }

    @Operation(summary = "Obtener un viaje por su identificación",
            description = "Obtener un viaje en base a su identificación proporcionada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontré el viaje",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Ride.class)) }),
            @ApiResponse(responseCode = "400", description = "ID proporcionada no válida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(rideService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Viaje inexistente");
        }

    }

    @Operation(summary = "Crear un nuevo viaje",
            description = "Crea un nuevo viaje con los datos proporcionados en el cuerpo de la solicitud.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operación exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Ride.class)) }),
            @ApiResponse(responseCode = "406", description = "Datos de viaje no válidos",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @PostMapping("")
    public ResponseEntity<?> save( @RequestBody @Validated DTORequestRide request ){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(rideService.save(request));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Ocurrio un error, revise los campos ingresados");
        }
    }

    @Operation(summary = "Eliminar un viaje por su identificación",
            description = "Elimina un viaje en base a su identificación proporcionada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje eliminado exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "ID proporcionada no válida",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            this.rideService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente el viaje con el id: " + id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo eliminar el viaje con id: " + id);
        }
    }

    @Operation(summary = "Actualizar un viaje por su identificación",
            description = "Actualiza un viaje en base a su identificación proporcionada y los datos en el cuerpo de la solicitud.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOResponseScooter.class)) }),
            @ApiResponse(responseCode = "400", description = "ID proporcionada no válida o datos de viaje no válidos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Validated DTORequestRide request) {
        try {
            Ride ride = rideService.update(id, request);
            DTOResponseRide response = new DTOResponseRide(ride);

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el viaje con el ID proporcionado.");
        }
    }

///////////////////////////////////////////////// SERVICIOS-REPORTES //////////////////////////////////////////////////////////////////////////

    //3.d. Como administrador quiero consultar el total facturado en un rango de meses de cierto año.
    @Operation(summary = "Consultar el total facturado en un rango de meses de un año",
            description = "Permite consultar el monto total facturado durante un rango de meses de un año específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOResponseCharged.class)) }),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud",
                    content = @Content)
    })
    @GetMapping("/facturado/anio/{anio}/mes-desde/{mes_inicio}/mes-hasta/{mes_fin}")
    public ResponseEntity<?> getTotalCharged(@PathVariable int anio, @PathVariable int mes_inicio, @PathVariable int mes_fin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(rideService.getTotalCharged(anio,mes_inicio,mes_fin));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo calcular lo facturado");
        }

    }

}
