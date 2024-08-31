package com.neusoft.elmboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.po.Orders;
import com.neusoft.elmboot.service.OrdersService;

@RestController
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;
	
	//@RequestMapping("/listOrdersByUserId")
	@GetMapping("/user/{userId}")
	public List<Orders> listOrdersByUserId(@PathVariable String userId) throws Exception{
		return ordersService.listOrdersByUserId(userId);
	}
	
	//@RequestMapping("/getOrdersById")
	@GetMapping("/{orderId}")
	public Orders getOrdersById(@PathVariable Integer orderId) throws Exception{
		return ordersService.getOrdersById(orderId);
	}
	
	//@RequestMapping("/createOrders")
	@PostMapping
	public int createOrders(@RequestBody Orders orders) throws Exception{
		return ordersService.createOrders(orders);
	}

}
