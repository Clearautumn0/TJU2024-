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

import com.neusoft.elmboot.po.DeliveryAddress;
import com.neusoft.elmboot.service.DeliveryAddressService;

@RestController
@RequestMapping("/delivery-addresses")
public class DeliveryAddressController {

	@Autowired
	private DeliveryAddressService deliveryAddressService;
	
	//@RequestMapping("/listDeliveryAddressByUserId")
	@GetMapping("/user/{userId}")
	public List<DeliveryAddress> listDeliveryAddressByUserId(@PathVariable String userId) throws Exception{
		return deliveryAddressService.listDeliveryAddressByUserId(userId);
	} 
	
	//@RequestMapping("/getDeliveryAddressById")
	@GetMapping("/{daId}")
	public DeliveryAddress getDeliveryAddressById(@PathVariable Integer daId) throws Exception{
		return deliveryAddressService.getDeliveryAddressById(daId);
	} 
	
	//@RequestMapping("/saveDeliveryAddress")
	@PostMapping
	public Map<String, Integer> saveDeliveryAddress(@RequestBody DeliveryAddress deliveryAddress) throws Exception{
		Map<String, Integer> response = new HashMap<>();
		response.put("data", deliveryAddressService.saveDeliveryAddress(deliveryAddress));
		return response;
	} 
	
	//@RequestMapping("/updateDeliveryAddress")
	@PutMapping
	public Map<String, Integer> updateDeliveryAddress(@RequestBody DeliveryAddress deliveryAddress) throws Exception{
		Map<String, Integer> response = new HashMap<>();
		response.put("data", deliveryAddressService.updateDeliveryAddress(deliveryAddress));
		return response;
	} 
	
	//@RequestMapping("/removeDeliveryAddress")
	@DeleteMapping("/{daId}")
	public Map<String, Integer> removeDeliveryAddress(@PathVariable Integer daId) throws Exception{
		Map<String, Integer> response = new HashMap<>();
		response.put("data", deliveryAddressService.removeDeliveryAddress(daId));
		return response;
	} 
}
