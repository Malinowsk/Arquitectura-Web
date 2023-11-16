package com.example.microservices_admin_maintenance.controller;

import com.example.microservices_admin_maintenance.dto.DTORequestScooter;
import com.example.microservices_admin_maintenance.dto.DTORequestScooterModel;
import com.example.microservices_admin_maintenance.dto.DTORequestStatusAccount;
import com.example.microservices_admin_maintenance.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/admin")
public class AdministrationController {

    private final AdminService adminService;

    public AdministrationController(AdminService adminService) {
        this.adminService = adminService;
    }

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
    public ResponseEntity<?> createScooter(@RequestBody @Validated DTORequestScooterModel scooterModel,@RequestHeader HttpHeaders headers) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.createScooter(scooterModel,headers));
        } catch (Exception e) {
            if(e.getMessage().contains("403"))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No cuenta con el rol necesario.");
            else if (e.getMessage().contains("401")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authenticación no válida.");
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    //Ubicar monopatín en parada (opcional)
    @Operation(summary = "Ubicar un monopatín en una parada",
            description = "Permite ubicar como administrador un monopatín en una parada específica.")
    @Parameter(name = "Authorization", description = "Token", required = true, example = "Bearer your_access_token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Monopatín ubicado exitosamente en la parada",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @PutMapping("/paradas/{station_id}")
    public ResponseEntity<?> assignScooterToStation(@PathVariable Long station_id, @RequestBody @Validated DTORequestScooter scooterDTO,@RequestHeader HttpHeaders headers) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.assignScooterToStation(station_id, scooterDTO,headers));
        } catch (Exception e) {
            if(e.getMessage().contains("403"))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No cuenta con el rol necesario.");
            else if (e.getMessage().contains("401")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authenticación no válida.");
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

////////////////////////////////////////////SERVICIOS-REPORTES////////////////////////////////////////////////////////////////////////

    //3.b. Como administrador quiero poder anular cuentas para inhabilitar el uso momentáneo de la misma.
    @Operation(summary = "Cambiar el estado de una cuenta",
            description = "Permite a un administrador cambiar el estado de una cuenta de usuario.")
    @Parameter(name = "Authorization", description = "Token", required = true, example = "Bearer your_access_token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado de la cuenta cambiado exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @PutMapping("/cuentas/{id}/estado-cuenta")
    public ResponseEntity<?> changeAccountStatus(@PathVariable Long id, @RequestBody @Validated DTORequestStatusAccount accDTO,@RequestHeader HttpHeaders headers) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.changeAccountStatus(id, accDTO, headers));
        } catch (Exception e) {
            if(e.getMessage().contains("403"))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No cuenta con el rol necesario.");
            else if (e.getMessage().contains("401")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authenticación no válida.");
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }


    //3.C. Como administrador quiero consultar los monopatines con más de X viajes en un cierto año.
    @Operation(summary = "Consultar monopatines con más de X viajes en un año",
            description = "Permite a un administrador consultar los monopatines que han tenido más de X viajes en un cierto año.")
    @Parameter(name = "Authorization", description = "Token", required = true, example = "Bearer your_access_token")
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
    public ResponseEntity<?> scooterReportByAmountOfTripsAndYear(@PathVariable int trip_qty, @PathVariable int year,@RequestHeader HttpHeaders headers) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.scooterReportByAmountOfTripsAndYear(trip_qty, year,headers));
        } catch (Exception e) {
            if(e.getMessage().contains("403"))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No cuenta con el rol necesario.");
            else if (e.getMessage().contains("401")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authenticación no válida.");
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    //3.d. Como administrador quiero consultar el total facturado en un rango de meses de cierto año.
    @Operation(summary = "Consultar el total facturado en un rango de meses de un año",
            description = "Permite a un administrador consultar el monto total facturado en un rango de meses de un año específico.")
    @Parameter(name = "Authorization", description = "Token", required = true, example = "Bearer your_access_token")
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
    public ResponseEntity<?> amountEarnedInTimePeriod(@PathVariable int year, @PathVariable int start, @PathVariable int end, @RequestHeader HttpHeaders headers) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.amountEarnedInTimePeriod(year, start, end, headers));
        } catch (Exception e) {
            if(e.getMessage().contains("403"))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No cuenta con el rol necesario.");
            else if (e.getMessage().contains("401")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authenticación no válida.");
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    //3.E. Como administrador quiero consultar la cantidad de monopatines actualmente en operación, versus la cantidad de monopatines actualmente en mantenimiento.
    @Operation(summary = "Consultar cantidad de monopatines en operación y en mantenimiento",
            description = "Permite a un administrador consultar la cantidad de monopatines actualmente en operación y la cantidad de monopatines en mantenimiento.")
    @Parameter(name = "Authorization", description = "Token", required = true, example = "Bearer your_access_token")
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
    public ResponseEntity<?> quantityOfScootersInOperation(@RequestHeader HttpHeaders headers) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.quantityOfScootersInOperation(headers));
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
