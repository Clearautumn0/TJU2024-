package com.neusoft.elmboot.controller;

import java.util.List;

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
	public Integer registerBusiness(@RequestBody Business business) {
		return businessService.registerBusiness(business);
	}
	
	@PutMapping
	public Integer updateBusiness(@RequestBody Business business) {
		return businessService.updateBusiness(business);
	}
	
//	@DeleteMapping("/{businessId}")
//	public Integer deleteBusiness(@PathVariable Integer businessId) {
//		return businessService.deleteBusiness(businessId);
//	}

}
