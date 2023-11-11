package com.example.microservices_scooters.controller;

import com.example.microservices_scooters.dto.DTORequestScooter;
import com.example.microservices_scooters.dto.DTORespondeStatusQualityScooter;
import com.example.microservices_scooters.dto.DTOResponseReport;
import com.example.microservices_scooters.dto.DTOResponseScooter;
import com.example.microservices_scooters.entity.Scooter;
import com.example.microservices_scooters.service.ScooterService;
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
@RequestMapping("api/monopatines")
@RequiredArgsConstructor
public class ScooterController {

    @Autowired
    private final ScooterService scooterService;

///////////////////////////////////////////////// ABM //////////////////////////////////////////////////////////////////////////
    @Operation(summary = "Obtener una lista de monopatines",
            description = "Obtiene una lista de todos los monopatines disponibles en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOResponseScooter.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("")
    public List<DTOResponseScooter> findAll(){
        return this.scooterService.findAll();
    }

    @Operation(summary = "Obtener un monopatín por su identificación",
            description = "Obtener un monopatín en base a su identificación proporcionada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontré el monopatin",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Scooter.class)) }),
            @ApiResponse(responseCode = "400", description = "ID proporcionada no válida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Monopatín no encontrado",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Monopatin inexistente");
        }
    }

    @Operation(summary = "Crear un nuevo monopatín",
            description = "Crea un nuevo monopatín con los datos proporcionados en el cuerpo de la solicitud.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operación exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Scooter.class)) }),
            @ApiResponse(responseCode = "406", description = "Datos de monopatín no válidos",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody @Validated DTORequestScooter request ){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(scooterService.save(request));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Ocurrio un error, revise los campos ingresados");
        }
    }

    @Operation(summary = "Eliminar un monopatín por su identificación",
            description = "Elimina un monopatín en base a su identificación proporcionada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Monopatín eliminado exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "ID proporcionada no válida",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            this.scooterService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente monopatin con el id: " + id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo eliminar el monopatin con id: " + id);
        }
    }

    @Operation(summary = "Actualizar un monopatín por su identificación",
            description = "Actualiza un monopatín en base a su identificación proporcionada y los datos en el cuerpo de la solicitud.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOResponseScooter.class)) }),
            @ApiResponse(responseCode = "400", description = "ID proporcionada no válida o datos de monopatín no válidos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Monopatín no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Validated DTORequestScooter request) {
        try {
            Scooter scooter = scooterService.update(id, request);
            DTOResponseScooter response = new DTOResponseScooter(scooter);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el monopatin con el ID proporcionado.");
        }
    }

///////////////////////////////////////////FUNCIONALIDADES/////////////////////////////////////////////////////////////////////////

    //Generar reporte de uso de monopatines por kilómetros
    //Generar reporte de uso de monopatines por tiempo con pausas
    //Generar reporte de uso de monopatines por tiempo sin pausas
    @Operation(summary = "Obtener un informe de monopatines ordenado por algún criterio",
            description = "Obtiene un informe de monopatines ordenado según el criterio especificado en el parámetro 'ordering'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informe generado exitosamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOResponseReport.class)) }),
            @ApiResponse(responseCode = "400", description = "Parámetro de orden no válido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Tipo de ordenamiento no válido",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("reportes/ordenado-por/{ordering}")
    public ResponseEntity<?> getReport(@PathVariable String ordering){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.getReport(ordering));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo generar el reporte");
        }
    }

    //Ubicar monopatín en parada
    @Operation(summary = "Ubicar un monopatín en una parada",
            description = "Ubica un monopatín en una parada específica según los identificadores proporcionados en los parámetros.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Monopatín ubicado exitosamente en la parada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOResponseScooter.class)) }),
            @ApiResponse(responseCode = "404", description = "Monopatín o parada no encontrados",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @PutMapping("{id}/paradas/{id_station}")
    public ResponseEntity<?> maintenance(@PathVariable Long id,@PathVariable Long id_station, @RequestBody @Validated DTORequestScooter request) {
        try {
            Scooter scooter = scooterService.maintenance(id_station,id, request);
            DTOResponseScooter response = new DTOResponseScooter(scooter);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el monopatin con el ID proporcionado.");
        }
    }

    //Registrar fin de mantenimiento de monopatín
    @Operation(summary = "Registrar fin de mantenimiento de un monopatín en una parada",
            description = "Registra el fin de mantenimiento de un monopatín en una parada específica según el identificador proporcionado en el parámetro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fin de mantenimiento registrado exitosamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOResponseScooter.class)) }),
            @ApiResponse(responseCode = "404", description = "Monopatín o parada no encontrados",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @PutMapping("finalizar-mantenimiento/paradas/{id_station}")
    public ResponseEntity<?> endMaintenance(@PathVariable Long id_station, @RequestBody @Validated DTORequestScooter request) {
        try {
            Scooter scooter = scooterService.endMaintenance(id_station, request);
            DTOResponseScooter response = new DTOResponseScooter(scooter);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el monopatin con el ID proporcionado.");
        }
    }

////////////////////////////////////////////SERVICIOS-REPORTES////////////////////////////////////////////////////////////////////////

    //3.a. Como encargado de mantenimiento quiero poder generar un reporte de uso de monopatines por
    //kilómetros para establecer si un monopatín requiere de mantenimiento. Este reporte debe poder
    //configurarse para incluir (o no) los tiempos de pausa.
    @Operation(summary = "Obtener un informe de monopatines por kilómetros con/sin pausas",
            description = "Obtiene un informe de monopatines ordenado por cantidad de kilometros y basado en la presencia de tiempo con/sin pausas según el valor del parámetro 'with_pause'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informe generado exitosamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOResponseReport.class)) }),
            @ApiResponse(responseCode = "400", description = "Parámetro 'with_pause' no válido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Informe no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("/reporte-monopatines-por-km/con-pausas/{boolValue}")
    public ResponseEntity<?> getScootersOfKms(@PathVariable("boolValue") String with_pause){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.getScootersOfKms(with_pause));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo generar el reporte");
        }
    }

    //3.c. Como administrador quiero consultar los monopatines con más de X viajes en un cierto año.
    @Operation(summary = "Consultar los monopatines con más de X viajes en un año específico",
            description = "Consulta los monopatines que han realizado más de X viajes en un año determinado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOResponseScooter.class)) }),
            @ApiResponse(responseCode = "400", description = "Solicitud no válida",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("/cantidad-viajes/{cant}/anio/{anio}")
    public ResponseEntity<?> getBySearch(@PathVariable int cant, @PathVariable int anio){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.getBySearch(cant,anio));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Viaje inexistente");
        }

    }
    //3.e. Como administrador quiero consultar la cantidad de monopatines actualmente en operación, versus la cantidad de monopatines actualmente en mantenimiento
    @Operation(summary = "Consultar la cantidad de monopatines en operación y en mantenimiento",
            description = "Consulta la cantidad de monopatines que se encuentran actualmente en operación y en mantenimiento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTORespondeStatusQualityScooter.class)) }),
            @ApiResponse(responseCode = "400", description = "Solicitud no válida",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("/operacion-vs-mantenimiento")
    public ResponseEntity<?> getQuantityBasedOnStatus(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.getQuantityBasedOnStatus());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error.");
        }

    }

    //3.g. Como usuario quiero lun listado de los monopatines cercanos a mi zona, para poder encontrar un monopatín cerca de mi ubicación
    @Operation(summary = "Obtener un listado de monopatines cercanos a una ubicación",
            description = "Obtiene un listado de monopatines que están cerca de la ubicación especificada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOResponseScooter.class)) }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("/alrededores/{id}")
    public ResponseEntity<?> getScootersSurroundings(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.getScootersSurroundings(id));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error.");
        }

    }


}