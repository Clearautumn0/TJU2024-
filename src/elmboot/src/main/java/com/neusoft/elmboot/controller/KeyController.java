package com.neusoft.elmboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.service.KeyService;




@RestController
public class KeyController {
		
	@Autowired
	private KeyService keyService;
	
	@GetMapping("/public-key")
	  public String getPublicKey() throws Exception {
		  return keyService.getPublicKey();
	  }

}
