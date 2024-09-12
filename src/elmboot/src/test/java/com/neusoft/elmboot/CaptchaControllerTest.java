package com.neusoft.elmboot;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.neusoft.elmboot.controller.CaptchaController;
import com.neusoft.elmboot.service.CaptchaService;

@WebMvcTest(CaptchaController.class)
public class CaptchaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CaptchaService captchaService;

    @MockBean
    private HttpSession httpSession;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCaptcha() throws Exception {
        // Mocking the captchaService response
        byte[] captchaBytes = "captcha".getBytes();
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(captchaBytes, HttpStatus.OK);
        when(captchaService.getCaptcha(any(HttpSession.class))).thenReturn(responseEntity);

        mockMvc.perform(get("/captcha"))
               .andExpect(status().isOk())
               .andExpect(content().bytes(captchaBytes));
    }

//    @Test
//    public void testVerifyCaptcha() throws Exception {
//        // Mocking the captchaService response
//        Map<String, String> responseMap = new HashMap<>();
//        responseMap.put("status", "success");
//        ResponseEntity<?> responseEntity = new ResponseEntity<>(responseMap, HttpStatus.OK);
//        when(captchaService.verifyCaptcha(anyString(), any(HttpSession.class))).thenReturn((ResponseEntity<?>) responseEntity);
//
//        Map<String, String> requestPayload = new HashMap<>();
//        requestPayload.put("captcha", "testCaptcha");
//
//        mockMvc.perform(post("/captcha")
//               .contentType("application/json")
//               .content("{\"captcha\":\"testCaptcha\"}"))
//               .andExpect(status().isOk())
//               .andExpect(jsonPath("$.status").value("success"));
//    }
}

