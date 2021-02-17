package com.iti.challenge.challenge.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iti.challenge.challenge.dto.PasswordDTO;
import com.iti.challenge.challenge.service.PasswordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(PasswordController.class)
public class PasswordControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PasswordService service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void callingWithPasswordCorrectShouldReturnOK() throws Exception {
        PasswordDTO passwordDTO = new PasswordDTO();
        passwordDTO.setSenha("AbTp9!fok");
        Mockito.when(service.valid(passwordDTO)).thenReturn(true);
        mockMvc.perform(post("/api/pwd/valid")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(passwordDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("true")));
    }

    @Test
    public void callingWithBlankSpaceShouldReturnBadRequest() throws Exception {
        PasswordDTO passwordDTO = new PasswordDTO();
        passwordDTO.setSenha("AbTp9 fok");
        Mockito.when(service.valid(passwordDTO)).thenThrow(new RuntimeException("false"));
        mockMvc.perform(post("/api/pwd/valid")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(passwordDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("false")));
    }

    @Test
    public void callingWithCaracterRepeatedShouldReturnBadRequest() throws Exception {
        PasswordDTO passwordDTO = new PasswordDTO();
        passwordDTO.setSenha("AbTp9!fok!");
        Mockito.when(service.valid(passwordDTO)).thenThrow(new RuntimeException("false"));
        mockMvc.perform(post("/api/pwd/valid")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(passwordDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("false")));
    }

    @Test
    public void callingWithLowerPasswordLenghtOrNullShouldReturnInternalServer() throws Exception {
        PasswordDTO passwordDTO = new PasswordDTO();
        passwordDTO.setSenha("AbTp9!");
        Mockito.when(service.valid(passwordDTO)).thenThrow(new IllegalArgumentException("false"));
        mockMvc.perform(post("/api/pwd/valid")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(passwordDTO)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("false")));
    }
}
