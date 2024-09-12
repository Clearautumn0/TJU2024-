package com.neusoft.elmboot;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import com.neusoft.elmboot.controller.KeyController;
import com.neusoft.elmboot.service.KeyService;

@WebMvcTest(KeyController.class)
public class KeyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KeyService keyService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPublicKey() throws Exception {
        // Mocking the KeyService response
        String publicKey = "mockedPublicKey";
        when(keyService.getPublicKey()).thenReturn(publicKey);

        // Expected response body
        Map<String, String> expectedResponse = new HashMap<>();
        expectedResponse.put("data", publicKey);

        mockMvc.perform(get("/public-key"))
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
               .andExpect(jsonPath("$.data").value(publicKey));
    }
}
