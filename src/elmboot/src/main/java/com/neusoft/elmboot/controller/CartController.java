package com.neusoft.elmboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.po.Cart;
import com.neusoft.elmboot.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {

	@Autowired
	private CartService cartService;
	
	//@RequestMapping("/listCart")
	@GetMapping("/user")
	public List<Cart> listCart(@RequestParam(required = true) String userId, 
			@RequestParam(required = false) Integer businessId) throws Exception{
		return cartService.listCart(userId, businessId);
	}
	
	//public List<Cart> listCart(@RequestBody Cart cart) throws Exception{
	//	return cartService.listCart(cart);
	//}
	
	//@RequestMapping("/saveCart")
	@PostMapping
	public Map<String, Integer> saveCart(@RequestBody Cart cart) throws Exception{
		Map<String, Integer> response = new HashMap<>();
		response.put("data", cartService.saveCart(cart));
		return response;
	}
	
	//@RequestMapping("/updateCart")
	@PutMapping
	public Map<String, Integer> updateCart(@RequestBody Cart cart) throws Exception{
		Map<String, Integer> response = new HashMap<>();
		response.put("data", cartService.updateCart(cart));
		return response;
	}
	
	@DeleteMapping
	public Map<String, Integer> removeCart(@RequestBody Cart cart) throws Exception{
		Map<String, Integer> response = new HashMap<>();
		response.put("data", cartService.removeCart(cart));
		return response;
	}
}
