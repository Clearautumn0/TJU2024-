package com.neusoft.elmboot.controller;

import java.util.List;

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
		Cart cart = new Cart();
		cart.setUserId(userId);
		cart.setBusinessId(businessId);
		return cartService.listCart(cart);
	}
	
	//public List<Cart> listCart(@RequestBody Cart cart) throws Exception{
	//	return cartService.listCart(cart);
	//}
	
	//@RequestMapping("/saveCart")
	@PostMapping
	public int saveCart(@RequestBody Cart cart) throws Exception{
		return cartService.saveCart(cart);
	}
	
	//@RequestMapping("/updateCart")
	@PutMapping
	public int updateCart(@RequestBody Cart cart) throws Exception{
		return cartService.updateCart(cart);
	}
	
	@DeleteMapping
	public int removeCart(@RequestBody Cart cart) throws Exception{
		return cartService.removeCart(cart);
	}
}
