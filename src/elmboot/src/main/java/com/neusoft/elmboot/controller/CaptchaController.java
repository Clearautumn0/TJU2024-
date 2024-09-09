package com.neusoft.elmboot.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.service.CaptchaService;

@RestController
public class CaptchaController {

	@Autowired
	private CaptchaService captchaService;
	@GetMapping("/captcha")
	public ResponseEntity<byte[]> getCaptcha(HttpSession session) throws IOException {
		return captchaService.getCaptcha(session);
	}
	@PostMapping("/captcha")
	public ResponseEntity<?> verifyCaptcha(@RequestBody Map<String, String> payload, HttpSession session) {
	    String captchaInput = payload.get("captcha");
	    return captchaService.verifyCaptcha(captchaInput, session);
	}

}
