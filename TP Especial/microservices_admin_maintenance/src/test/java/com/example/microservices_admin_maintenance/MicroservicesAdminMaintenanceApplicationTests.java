package com.example.microservices_admin_maintenance;

import com.example.microservices_admin_maintenance.controller.AdministrationController;
import com.example.microservices_admin_maintenance.controller.FareController;
import com.example.microservices_admin_maintenance.dto.DTOFareResponse;
import com.example.microservices_admin_maintenance.service.FareService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@SpringBootTest
class MicroservicesAdminMaintenanceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FareService fareService;

    DTOFareResponse frDTO = new DTOFareResponse("654123", "comun", 150L, 200L);

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    public void getById() throws Exception {
        Mockito.when(
                fareService.findById(Mockito.anyString())
        ).thenReturn(frDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/tarifas/6546a76d8703784d0d60d935")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getContentAsString());

        String expected = "{\"id\":\"654123\",\"name\":\"comun\",\"cost_per_min\":150.0,\"extended_pause_cost\":200.0}";

        System.out.println(expected);

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
    @Test
    void contextLoads() throws Exception {
    }



}
