package com.example.microservices_users;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.microservices_users.dto.DTORequestAccount;
import com.example.microservices_users.dto.DTOResponseAccount;
import com.example.microservices_users.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
class MicroservicesUsersApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountService accountService;

    @Test
    void testFindAll() throws Exception {
        List<DTOResponseAccount> accounts = Collections.singletonList(new DTOResponseAccount());

        when(accountService.findAll()).thenReturn(accounts);

        mockMvc.perform(get("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDMxNjM0Nn0.ZDvDEdCGWd2PsWiLuYrQFVDfP7ybJd1Do2gLOZX3aEUw_EQbhstEtJK0KPvym2T_G3eshYqV7UJH_OKtpd37aQ"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(accounts.size()));
    }

    @Test
    void testGetAccountByID() throws Exception {
        long accountId = 1L;
        DTOResponseAccount account = new DTOResponseAccount();

        when(accountService.findById(accountId)).thenReturn(account);

        mockMvc.perform(get("/api/accounts/{id}", accountId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDMxNjM0Nn0.ZDvDEdCGWd2PsWiLuYrQFVDfP7ybJd1Do2gLOZX3aEUw_EQbhstEtJK0KPvym2T_G3eshYqV7UJH_OKtpd37aQ"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.active").value(false));
    }

    @Test
    void testSave() throws Exception {
        DTORequestAccount request = new DTORequestAccount();
        DTOResponseAccount savedAccount = new DTOResponseAccount();

        when(accountService.save(request)).thenReturn(savedAccount);

        mockMvc.perform(post("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDMxNjM0Nn0.ZDvDEdCGWd2PsWiLuYrQFVDfP7ybJd1Do2gLOZX3aEUw_EQbhstEtJK0KPvym2T_G3eshYqV7UJH_OKtpd37aQ"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(savedAccount.getId()));
    }

    @Test
    void contextLoads() {
    }

}
