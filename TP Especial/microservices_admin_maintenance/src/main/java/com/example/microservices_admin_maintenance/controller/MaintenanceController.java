package com.example.microservices_admin_maintenance.controller;

import com.example.microservices_admin_maintenance.dto.*;
import com.example.microservices_admin_maintenance.service.MaintenanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/mantenimientos")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

///////////////////////////////////////////////////////////// ABM ////////////////////////////////////////////////////////////////////////

    @Operation(summary = "Obtener una lista de monopatines en mantenimiento",
            description = "Obtiene una lista de todos los monopatines en mantenimiento disponibles en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOResponseMaintenance.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("")
    public List<DTOResponseMaintenance> findAll(){ return this.maintenanceService.findAll(); }

    @Operation(summary = "Obtener mantenimiento por su identificación",
            description = "Obtener mantenimiento en base a su identificación proporcionada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontré el mantenimiento",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOResponseMaintenance.class)) }),
            @ApiResponse(responseCode = "400", description = "ID proporcionada no válida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Mantenimiento no encontrada",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maintenanceService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Orden de mantenimiento inexistente");
        }
    }


    @Operation(summary = "Eliminar un mantenimiento por su identificación",
            description = "Elimina un mantenimiento en base a su identificación proporcionada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mantenimiento eliminado exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "ID proporcionada no válida",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            this.maintenanceService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se eliminó correctamente el registro de mantenimiento con id: " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo eliminar el registro de mantenimiento con id: " + id);
        }
    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Validated DTORequestMaintenance rDTO) {
        try {
            DTOResponseMaintenance response = maintenanceService.update(id, rDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el registro de mantenimiento con el ID proporcionado.");
        }
    }*/

////////////////////////////////////////////FUNCIONALIDADES////////////////////////////////////////////////////////////////////////

    //Agregar Monopatín a Mantenimiento, Comunicarse con Mic Monopatines para cambiar su estado y removerlo de la parada
    @Operation(summary = "Crear un nuevo mantenimiento",
            description = "Crea un nuevo mantenimiento con los datos proporcionados en el cuerpo de la solicitud.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operación exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOResponseMaintenance.class)) }),
            @ApiResponse(responseCode = "406", description = "Datos de mantenimiento no válidos",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody @Validated DTORequestMaintenance rDTO,@RequestHeader HttpHeaders headers) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maintenanceService.save(rDTO,headers));
        } catch (Exception e) {
            if(e.getMessage().contains("403"))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No cuenta con el rol necesario.");
            else if (e.getMessage().contains("401")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authenticación no válida.");
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    //Registrar fin de mantenimiento de monopatín
    @Operation(summary = "Registrar fin de mantenimiento de monopatín",
            description = "Permite registrar que un monopatín ha finalizado su proceso de mantenimiento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro de fin de mantenimiento exitoso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOResponseMaintenance.class))),
            @ApiResponse(responseCode = "404", description = "No se encontró el registro de mantenimiento",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error al registrar el fin de mantenimiento",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> finishMaintenance(@PathVariable String id,@RequestHeader HttpHeaders headers) {
        try {
            DTOResponseMaintenance response = maintenanceService.endScooterMaintenance(id,headers);
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

    //Generar reporte de uso de monopatines por kilómetros
    //Generar reporte de uso de monopatines por tiempo con pausas
    //Generar reporte de uso de monopatines por tiempo sin pausas
    @Operation(summary = "Generar reporte de uso de monopatines",
            description = "Genera un reporte de uso de monopatines según el campo especificado (kilómetros, tiempo con pausas, tiempo sin pausas).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte generado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOResponseReport[].class))),
            @ApiResponse(responseCode = "404", description = "No se pudo generar el reporte",
                    content = @Content)
    })
    @GetMapping("/reporte-monopatines-por/{campo}")
    public ResponseEntity<?> getReportBy(@PathVariable String campo,@RequestHeader HttpHeaders headers) {
        try {
            DTOResponseReport[] response = maintenanceService.getReportBy(campo,headers);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            if(e.getMessage().contains("403"))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No cuenta con el rol necesario.");
            else if (e.getMessage().contains("401")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authenticación no válida.");
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo generar el reporte.");
        }
    }

////////////////////////////////////////////SERVICIOS-REPORTES////////////////////////////////////////////////////////////////////////

    //3.a. Como encargado de mantenimiento quiero poder generar un reporte de uso de monopatines por
    //kilómetros para establecer si un monopatín requiere de mantenimiento. Este reporte debe poder
    //configurarse para incluir (o no) los tiempos de pausa.
    @Operation(summary = "Obtener un informe de monopatines por kilómetros con/sin pausas",
            description = "Obtiene un informe de monopatines ordenado por cantidad de kilometros y basado en la presencia de tiempo con/sin pausas según el valor del parámetro 'with_pause'.")
    @Parameter(name = "Authorization", description = "Token", required = true, example = "Bearer your_access_token")
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
    @GetMapping("/reporte-monopatines-por-km/con-pausas/{stringBoolean}")
    public ResponseEntity<?> getReportByKmOptionalPauseTime(@PathVariable String stringBoolean,@RequestHeader HttpHeaders headers) {
        try {
            DTOResponseScootersOfKms[] response = maintenanceService.getReportByKmOptionalPauseTime(stringBoolean,headers);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e) {
            if(e.getMessage().contains("403"))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No cuenta con el rol necesario.");
            else if (e.getMessage().contains("401")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authenticación no válida.");
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }
}
