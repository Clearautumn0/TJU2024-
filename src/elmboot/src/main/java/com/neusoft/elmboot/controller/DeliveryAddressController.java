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
	public int saveDeliveryAddress(@RequestBody DeliveryAddress deliveryAddress) throws Exception{
		return deliveryAddressService.saveDeliveryAddress(deliveryAddress);
	} 
	
	//@RequestMapping("/updateDeliveryAddress")
	@PutMapping
	public int updateDeliveryAddress(@RequestBody DeliveryAddress deliveryAddress) throws Exception{
		return deliveryAddressService.updateDeliveryAddress(deliveryAddress);
	} 
	
	//@RequestMapping("/removeDeliveryAddress")
	@DeleteMapping("/{daId}")
	public int removeDeliveryAddress(@PathVariable Integer daId) throws Exception{
		return deliveryAddressService.removeDeliveryAddress(daId);
	} 
}
