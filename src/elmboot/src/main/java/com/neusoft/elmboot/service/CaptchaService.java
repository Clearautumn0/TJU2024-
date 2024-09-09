package com.neusoft.elmboot.service;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;

public interface CaptchaService {
	public ResponseEntity<byte[]> getCaptcha(HttpSession session) throws IOException;

	public ResponseEntity<?> verifyCaptcha(String captchaInput, HttpSession session);
}
