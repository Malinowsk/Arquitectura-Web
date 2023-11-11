package com.example.microservices_admin_maintenance.controller;

import com.example.microservices_admin_maintenance.dto.DTORequestScooter;
import com.example.microservices_admin_maintenance.dto.DTORequestScooterModel;
import com.example.microservices_admin_maintenance.dto.DTORequestStatusAccount;
import com.example.microservices_admin_maintenance.service.AdminService;
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

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class AdministrationController {

    @Autowired
    private final AdminService adminService;


////////////////////////////////////////////FUNCIONALIDADES////////////////////////////////////////////////////////////////////////


    // Agregar monopatín
    @Operation(summary = "Agregar un monopatín",
            description = "Permite agregar coomo administrador un nuevo monopatín al sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Monopatín agregado exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @PostMapping("/monopatines")
    public ResponseEntity<?> createScooter(@RequestBody @Validated DTORequestScooterModel scooterModel) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.createScooter(scooterModel));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    //Ubicar monopatín en parada (opcional)
    @Operation(summary = "Ubicar un monopatín en una parada",
            description = "Permite ubicar como administrador un monopatín en una parada específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Monopatín ubicado exitosamente en la parada",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @PutMapping("/paradas/{station_id}")
    public ResponseEntity<?> assignScooterToStation(@PathVariable Long station_id, @RequestBody @Validated DTORequestScooter scooterDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.assignScooterToStation(station_id, scooterDTO));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

////////////////////////////////////////////SERVICIOS-REPORTES////////////////////////////////////////////////////////////////////////

    //3.b. Como administrador quiero poder anular cuentas para inhabilitar el uso momentáneo de la misma.
    @Operation(summary = "Cambiar el estado de una cuenta",
            description = "Permite a un administrador cambiar el estado de una cuenta de usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado de la cuenta cambiado exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @PutMapping("/cuentas/{id}/estado-cuenta")
    public ResponseEntity<?> changeAccountStatus(@PathVariable Long id, @RequestBody @Validated DTORequestStatusAccount accDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.changeAccountStatus(id, accDTO));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }


    //3.C. Como administrador quiero consultar los monopatines con más de X viajes en un cierto año.
    @Operation(summary = "Consultar monopatines con más de X viajes en un año",
            description = "Permite a un administrador consultar los monopatines que han tenido más de X viajes en un cierto año.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("/monopatines/cantidad-de-viajes-mayor-que/{trip_qty}/anio/{year}")
    public ResponseEntity<?> scooterReportByAmountOfTripsAndYear(@PathVariable int trip_qty, @PathVariable int year) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.scooterReportByAmountOfTripsAndYear(trip_qty, year));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    //3.d. Como administrador quiero consultar el total facturado en un rango de meses de cierto año.
    @Operation(summary = "Consultar el total facturado en un rango de meses de un año",
            description = "Permite a un administrador consultar el monto total facturado en un rango de meses de un año específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("/viajes/dinero-total-ganado-en/anio/{year}/desde-mes/{start}/hasta-mes/{end}")
    public ResponseEntity<?> amountEarnedInTimePeriod(@PathVariable int year, @PathVariable int start, @PathVariable int end) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.amountEarnedInTimePeriod(year, start, end));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    //3.E. Como administrador quiero consultar la cantidad de monopatines actualmente en operación, versus la cantidad de monopatines actualmente en mantenimiento.
    @Operation(summary = "Consultar cantidad de monopatines en operación y en mantenimiento",
            description = "Permite a un administrador consultar la cantidad de monopatines actualmente en operación y la cantidad de monopatines en mantenimiento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("/monopatines/cantidad-en-operacion-y-mantenimiento")
    public ResponseEntity<?> quantityOfScootersInOperation() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.quantityOfScootersInOperation());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }
}
