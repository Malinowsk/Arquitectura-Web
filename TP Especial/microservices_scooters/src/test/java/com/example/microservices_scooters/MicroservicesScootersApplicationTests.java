package com.example.microservices_scooters;

import com.example.microservices_scooters.dto.DTOResponseScooter;
import com.example.microservices_scooters.entity.Scooter;
import com.example.microservices_scooters.service.ScooterService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;



@AutoConfigureMockMvc
@SpringBootTest
class MicroservicesScootersApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScooterService scooterService;

    @Test
    void testFindAll() throws Exception {
        // Datos de ejemplo para la respuesta del servicio
        List<DTOResponseScooter> scooters = new ArrayList<>();

        Scooter scooter1;
        scooter1 = new Scooter("Toyota");
        scooter1.setKmsTraveled(1000.88);
        scooter1.setState("en_mantenimiento");
        scooter1.setTotalUsageTime(500L);
        scooter1.setPausedTime(50L);

        Scooter scooter2;
        scooter2 = new Scooter("Honda");
        scooter2.setKmsTraveled(1020.88);
        scooter2.setState("en_mantenimiento");

        DTOResponseScooter scooter3 = new DTOResponseScooter(scooter1);
        DTOResponseScooter scooter4 = new DTOResponseScooter(scooter2);
        scooters.add(scooter3);
        scooters.add(scooter4);

        // Configuración del comportamiento del servicio mock
        when(scooterService.findAll(any(HttpHeaders.class))).thenReturn(scooters);

        // Construir la solicitud
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/monopatines")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .header("Otro-Encabezado", "valor-otro-encabezado");

        // Realizar la solicitud y realizar afirmaciones
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.size()", is(scooters.size())))
                // .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].state", is("en_mantenimiento")))
                .andExpect(jsonPath("$[0].kmsTraveled", is(1000.88)))
                .andExpect(jsonPath("$[0].totalUsageTime", is(500)))
                .andExpect(jsonPath("$[0].pausedTime", is(50)))
                .andExpect(jsonPath("$[0].model", is("Toyota")))
                //.andExpect(jsonPath("$[1].id", is("2")))
                .andExpect(jsonPath("$[1].state", is("en_mantenimiento")))
                .andExpect(jsonPath("$[1].kmsTraveled", is(1020.88)))
                .andExpect(jsonPath("$[1].model", is("Honda")));
    }


    @Test
    void contextLoads() {
    }

}
