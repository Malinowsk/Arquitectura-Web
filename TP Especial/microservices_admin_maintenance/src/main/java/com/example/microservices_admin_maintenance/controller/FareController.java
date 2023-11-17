package com.example.microservices_admin_maintenance.controller;

import com.example.microservices_admin_maintenance.dto.DTOFareRequest;
import com.example.microservices_admin_maintenance.dto.DTOFareResponse;
import com.example.microservices_admin_maintenance.dto.DTOScheduledFareRequest;
import com.example.microservices_admin_maintenance.dto.DTOScheduledFareResponse;
import com.example.microservices_admin_maintenance.service.FareService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("api/tarifas")
public class FareController {

    private final FareService fareService;

    public FareController(FareService fareService) {
        this.fareService = fareService;
    }

    //////////////////////////////////////////// ABM ////////////////////////////////////////////////////////////////////////

    @Operation(summary = "Obtener una lista de tarifas",
            description = "Obtiene una lista de todas las tarifas disponibles en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOFareResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("")
    public ResponseEntity<?> findAll(@RequestHeader HttpHeaders headers) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.fareService.findAll(headers));
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

    @Operation(summary = "Obtener una tarifa por su identificación",
            description = "Obtener una tarifa en base a su identificación proporcionada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontré la tarifa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOFareResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "ID proporcionada no válida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Tarifa no encontrada",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id,@RequestHeader HttpHeaders headers) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(fareService.findById(id,headers));
        }catch (Exception e) {
            if(e.getMessage().contains("403"))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No cuenta con el rol necesario.");
            else if (e.getMessage().contains("401")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authenticación no válida.");
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    @Operation(summary = "Crear una nueva tarifa",
            description = "Crea una nueva tarifa con los datos proporcionados en el cuerpo de la solicitud.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operación exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOFareResponse.class)) }),
            @ApiResponse(responseCode = "406", description = "Datos de tarifa no válidos",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @PostMapping("")
    public ResponseEntity<?> addFare(@RequestBody @Validated DTOFareRequest fDTO,@RequestHeader HttpHeaders headers) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(fareService.addFare(fDTO,headers));
        }catch (Exception e) {
                if(e.getMessage().contains("403"))
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No cuenta con el rol necesario.");
                else if (e.getMessage().contains("401")) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authenticación no válida.");
                } else
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
            }
    }

    @Operation(summary = "Modificar una tarifa",
            description = "Modifica una tarifa con los datos proporcionados en el cuerpo de la solicitud.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarifa modificada exitosamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOFareResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Datos de tarifa no válidos",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFare(@PathVariable String id, @RequestBody @Validated DTOFareRequest fDTO,@RequestHeader HttpHeaders headers) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(fareService.updateFare(id, fDTO, headers));
        }catch (Exception e) {
            if(e.getMessage().contains("403"))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No cuenta con el rol necesario.");
            else if (e.getMessage().contains("401")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authenticación no válida.");
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    @Operation(summary = "Eliminar una tarifa",
            description = "Elimina una tarifa según la id ingresada en el path")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarifa eliminada exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "ID proporcionada no válida",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFare(@PathVariable String id,@RequestHeader HttpHeaders headers) {
        try {
            this.fareService.deleteFare(id,headers);
            return ResponseEntity.status(HttpStatus.OK).body("Se eliminó correctamente la tarifa con id: " + id);
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

    @Operation(summary = "Obtener una lista de tarifas programadas",
            description = "Obtiene una lista de todas las tarifas programadas disponibles en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOScheduledFareResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("/programadas")
    public ResponseEntity<?> getScheduledFares(@RequestHeader HttpHeaders headers) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.fareService.findAllScheduledFares(headers));
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

    @Operation(summary = "Modificar entidad de ajuste de precio programado",
            description = "Permite a un administrador modificar un ajuste de precios programado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modificación de tarifa programada exitosa.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOScheduledFareResponse.class))),
            @ApiResponse(responseCode = "400", description = "Error en los datos ingresados",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @PutMapping("/programadas/{id}")
    public ResponseEntity<?> updateScheduledFare(@PathVariable String id, @RequestBody @Validated DTOScheduledFareRequest sfDTO,@RequestHeader HttpHeaders headers) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.fareService.updateScheduledFare(id, sfDTO,headers));
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

    @Operation(summary = "Eliminar una entidad de ajuste de precio programado",
            description = "Permite a un administrador eliminar un ajuste de precios programado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eliminación de tarifa programada exitosa."),
            @ApiResponse(responseCode = "400", description = "Error en los datos ingresados",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @DeleteMapping("/programadas/{id}")
    public ResponseEntity<?> deleteScheduledFare(@PathVariable String id,@RequestHeader HttpHeaders headers) {
        try {
            this.fareService.deleteScheduledFare(id,headers);
            return ResponseEntity.status(HttpStatus.OK).body("Se eliminó correctamente la tarifa programada con id: " + id);
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

////////////////////////////////////////////SERVICIOS-REPORTES////////////////////////////////////////////////////////////////////////

    //3.f. Como administrador quiero hacer un ajuste de precios, y que a partir de cierta fecha el sistema habilite los nuevos precios.
    @Operation(summary = "Establecer ajuste de precios programado",
            description = "Permite a un administrador establecer un ajuste de precios programado en el sistema a partir de una fecha específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ajuste de precios programado creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DTOScheduledFareResponse.class))),
            @ApiResponse(responseCode = "400", description = "Error en los datos ingresados",
                    content = @Content)
    })
    @PostMapping("/programadas")
    public ResponseEntity<?> setScheduledFare(@RequestBody @Validated DTOScheduledFareRequest sfDTO,@RequestHeader HttpHeaders headers) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.fareService.addScheduledFareUpdate(sfDTO,headers));
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
