package com.example.microservices_scooters.controller;

import com.example.microservices_scooters.dto.DTORequestScooter;
import com.example.microservices_scooters.dto.DTORequestStation;
import com.example.microservices_scooters.dto.DTOResponseScooter;
import com.example.microservices_scooters.dto.DTOResponseStation;
import com.example.microservices_scooters.entity.Scooter;
import com.example.microservices_scooters.entity.Station;
import com.example.microservices_scooters.service.StationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@RestController
@RequestMapping("api/paradas")
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @Operation(summary = "Obtener una lista de estaciones",
            description = "Obtiene una lista de todas las estaciones de monopatín disponibles en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOResponseStation.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("")
    public ResponseEntity<?> findAll(@RequestHeader HttpHeaders headers){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.stationService.findAll(headers));
        } catch (Exception e) {
            if(e.getMessage().contains("403"))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No cuenta con el rol necesario.");
            else if (e.getMessage().contains("401")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authenticación no válida.");
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    @Operation(summary = "Obtener una estación por su identificación",
            description = "Obtener una estación en base a su identificación proporcionada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontré la estación",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Station.class)) }),
            @ApiResponse(responseCode = "400", description = "ID proporcionada no válida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Estación no encontrada",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id,@RequestHeader HttpHeaders headers){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(stationService.findById(id,headers));
        } catch (Exception e) {
            if(e.getMessage().contains("403"))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No cuenta con el rol necesario.");
            else if (e.getMessage().contains("401")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authenticación no válida.");
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    @Operation(summary = "Crear una nueva estación",
            description = "Crea una nueva estación con los datos proporcionados en el cuerpo de la solicitud.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operación exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Station.class)) }),
            @ApiResponse(responseCode = "406", description = "Datos de estación no válidos",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @PostMapping("")
    public ResponseEntity<?> save( @RequestBody @Validated DTORequestStation request ,@RequestHeader HttpHeaders headers){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(stationService.save(request,headers));
        } catch (Exception e) {
            if(e.getMessage().contains("403"))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No cuenta con el rol necesario.");
            else if (e.getMessage().contains("401")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authenticación no válida.");
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    @Operation(summary = "Eliminar una estación por su identificación",
            description = "Elimina una estación en base a su identificación proporcionada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estación eliminada exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "ID proporcionada no válida",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,@RequestHeader HttpHeaders headers){
        try {
            this.stationService.delete(id,headers);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la parada con el id: " + id);
        } catch (Exception e) {
            if(e.getMessage().contains("403"))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No cuenta con el rol necesario.");
            else if (e.getMessage().contains("401")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authenticación no válida.");
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    @Operation(summary = "Actualizar una estación por su identificación",
            description = "Actualiza una estación en base a su identificación proporcionada y los datos en el cuerpo de la solicitud.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOResponseStation.class)) }),
            @ApiResponse(responseCode = "400", description = "ID proporcionada no válida o datos de estación no válidos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Estación no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Validated DTORequestStation request,@RequestHeader HttpHeaders headers) {
        try {
            Station station = stationService.update(id, request,headers);
            DTOResponseStation response = new DTOResponseStation(station);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            if(e.getMessage().contains("403"))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No cuenta con el rol necesario.");
            else if (e.getMessage().contains("401")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authenticación no válida.");
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    //Agregamos scooter a estación
    @Operation(summary = "Agregar un monopatín a una estación",
            description = "Permite agregar un monopatín a una estación específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOResponseStation.class)) }),
            @ApiResponse(responseCode = "404", description = "No se encontró la estación o el monopatín",
                    content = @Content)
    })
    @PutMapping("/{id}/monopatines")
    public ResponseEntity<?> addScooterToStation(@PathVariable Long id, @RequestBody @Validated DTORequestScooter id_scooter) {
        try {
            Station station = stationService.addScooterToStation(id, id_scooter.getId());
            DTOResponseStation response = new DTOResponseStation(station);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}

