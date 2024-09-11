package com.neusoft.elmboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.neusoft.elmboot.controller.CaptchaController;
import com.neusoft.elmboot.service.CaptchaService;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(CaptchaController.class)
public class CaptchaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CaptchaService captchaService;

    @Test
    public void testGetCaptcha() throws Exception {
        byte[] captchaImageBytes = new byte[] { /* 模拟的验证码图片字节数据 */ };
        when(captchaService.getCaptcha(null)).thenReturn(ResponseEntity.ok(captchaImageBytes));

        mockMvc.perform(MockMvcRequestBuilders.get("/captcha")
                .accept(MediaType.IMAGE_PNG))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().bytes(captchaImageBytes));
    }

//    @Test
//    public void testVerifyCaptcha() throws Exception {
//        String correctCaptcha = "1234";
//        when(captchaService.verifyCaptcha(correctCaptcha, null)).thenReturn((ResponseEntity<?>) ResponseEntity.ok(true));
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/captcha")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"captcha\":\"" + correctCaptcha + "\"}"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
//
//        verify(captchaService).verifyCaptcha(correctCaptcha, null);
//    }
}