package com.neusoft.elmboot.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.neusoft.elmboot.service.CaptchaService;
import com.neusoft.elmboot.util.CaptchaUtil;

@Service
public class CaptchaServiceImpl implements CaptchaService {
	  private static final String CAPTCHA_SESSION_KEY = "captcha";
	@Override
	public ResponseEntity<byte[]> getCaptcha(HttpSession session) throws IOException {
        String captchaText = CaptchaUtil.generateCaptchaText(5); // 生成5位随机字母或数字的验证码
        session.setAttribute(CAPTCHA_SESSION_KEY, captchaText); // 将验证码保存到 session 中

        BufferedImage captchaImage = CaptchaUtil.createCaptchaImage(captchaText); // 生成验证码图片

        // 将图片转换成字节流以便传输
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(captchaImage, "png", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();

        // 返回图片作为响应
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return ResponseEntity.ok().headers(headers).body(imageInByte);
	}

	@Override
	public ResponseEntity<?> verifyCaptcha(String captchaInput, HttpSession session) {
	    String captchaStored = (String) session.getAttribute(CAPTCHA_SESSION_KEY);
	    
	    Map<String, Boolean> response = new HashMap<>();
	    
	    if (captchaStored != null && captchaStored.equalsIgnoreCase(captchaInput)) {
	        response.put("success", true);
	    } else {
	        response.put("success", false);
	    }
	    
	    return ResponseEntity.ok().body(response);
	}

}
