package com.neusoft.elmboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.service.KeyService;

@RestController
public class KeyController {
		
	@Autowired
	private KeyService keyService;
	
	@GetMapping("/public-key")
	  public Map<String, String> getPublicKey() throws Exception {
		Map<String, String> response = new HashMap<>();
		response.put("data", keyService.getPublicKey());
		return response;
	}

}
