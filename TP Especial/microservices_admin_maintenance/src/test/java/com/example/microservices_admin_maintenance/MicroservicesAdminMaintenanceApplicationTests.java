package com.example.microservices_admin_maintenance;

import com.example.microservices_admin_maintenance.controller.AdministrationController;
import com.example.microservices_admin_maintenance.controller.FareController;
import com.example.microservices_admin_maintenance.dto.DTOFareResponse;
import com.example.microservices_admin_maintenance.service.FareService;
import io.swagger.v3.core.util.Json;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
/*
    @Test
    @WithMockUser(authorities = {"ADMIN"})
    public void getById() throws Exception {

        //Raro esto pero no encuentro mucha info en como testear un metodo que devuelve ResponseEntity<?>, testee un par de formas aca
        //pero tiran error
        ResponseEntity testDTOResponse = new ResponseEntity(
                "{\"id\":\"654123\",\"name\":\"comun\",\"cost_per_min\":150.0,\"extended_pause_cost\":200.0}",
                new HttpHeaders(),
                HttpStatus.OK
        );

        //Si se borran esta y la parte anterior, da como fallido el test
        Mockito.when(
                fareController.findById(Mockito.anyString(),null)
        ).thenReturn(testDTOResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/tarifas/6546a76d8703784d0d60d935")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getContentAsString());

        String expected = "{\"id\":\"654123\",\"name\":\"comun\",\"cost_per_min\":150.0,\"extended_pause_cost\":200.0}";

        System.out.println(expected);

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void getAllFares() throws Exception {
        DTOFareResponse testFare1 = new DTOFareResponse("123", "test1", 20.0, 30.0);
        DTOFareResponse testFare2 = new DTOFareResponse("456", "test2", 40.0, 60.0);

        List<DTOFareResponse> testList = new ArrayList<DTOFareResponse>();
        testList.add(testFare1);
        testList.add(testFare2);

        Mockito.when(
                fareController.findAll()
        ).thenReturn(testList);

        RequestBuilder req = MockMvcRequestBuilders.get("/api/tarifas").accept(MediaType.APPLICATION_JSON);

        List<DTOFareResponse> resultList = fareController.findAll();

        mockMvc.perform(req)
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.size()")
                    .value(testList.size()))
                .andDo(print());

        //Revisar, el assert es necesario, pero esta bien planteado?
        JSONAssert.assertEquals(resultList.toString(), testList.toString(), false);
    }*/

    @Test
    void contextLoads() throws Exception {
    }



}
