package com.neusoft.elmboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.po.Business;
import com.neusoft.elmboot.service.BusinessService;

@RestController
@RequestMapping("/businesses")
public class BusinessController {
	
	@Autowired
	private BusinessService businessService;
	
	//@RequestMapping("/listBusinessByOrderTypeId")
	@GetMapping("/orderType/{orderTypeId}")
	public List<Business> listBusinessByOrderTypeId(@PathVariable Integer orderTypeId) throws Exception{
		return businessService.listBusinessByOrderTypeId(orderTypeId);
	}
	
	//@RequestMapping("/getBusinessById")
	@GetMapping("/{businessId}")
	public Business getBusinessById(@PathVariable Integer businessId) throws Exception{
		return businessService.getBusinessById(businessId);
	}
	
	@PostMapping
	public Map<String, Integer> registerBusiness(@RequestBody Business business) {
		Map<String, Integer> response = new HashMap<>();
		response.put("data", businessService.registerBusiness(business));
		return response;
	}
	
	@PutMapping
	public Map<String, Integer> updateBusiness(@RequestBody Business business) {
		Map<String, Integer> response = new HashMap<>();
		response.put("data", businessService.updateBusiness(business));
		return response;
	}
	
	@DeleteMapping("/{businessId}")
	public Map<String, Integer> removeBusiness(@PathVariable Integer businessId) {
		Map<String, Integer> response = new HashMap<>();
		response.put("data", businessService.removeBusiness(businessId));
		return response;
	}

}
