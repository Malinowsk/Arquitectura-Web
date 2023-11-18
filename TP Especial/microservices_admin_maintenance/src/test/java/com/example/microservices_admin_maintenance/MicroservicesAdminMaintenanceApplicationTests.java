package com.example.microservices_admin_maintenance;

import com.example.microservices_admin_maintenance.controller.AdministrationController;
import com.example.microservices_admin_maintenance.controller.FareController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class MicroservicesAdminMaintenanceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FareController fareController;

    @MockBean
    private AdministrationController adminController;



    @Test
    public void getFareById() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Connection", "keep-alive");
        headers.add("Accept-Encoding", "gzip, deflate, br");
        headers.add("Accept", "/");

        ResponseEntity testDTOResponse = new ResponseEntity(
                "{\"id\":\"654123\",\"name\":\"comun\",\"cost_per_min\":150.0,\"extended_pause_cost\":200.0}",
                headers,
                HttpStatus.OK
        );

        Mockito.when(
                fareController.findById(Mockito.anyString(), any(HttpHeaders.class))
        ).thenReturn(testDTOResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/tarifas/1")
                .accept(MediaType.APPLICATION_JSON).headers(headers);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is("654123")))
                .andReturn();
    }

    @Test
    public void getAllFares() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Connection", "keep-alive");
        headers.add("Accept-Encoding", "gzip, deflate, br");
        headers.add("Accept", "/");

        

    }

    @Test
    void contextLoads() throws Exception {
    }



}
