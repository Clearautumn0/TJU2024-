package com.neusoft.elmboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.elmboot.mapper.CartMapper;
import com.neusoft.elmboot.po.Cart;
import com.neusoft.elmboot.service.CartService;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartMapper cartMapper;

	@Override
	public List<Cart> listCart(String userId, Integer businessId) {
		Cart cart = new Cart();
		cart.setUserId(userId);
		cart.setBusinessId(businessId);
		return cartMapper.listCart(cart);
	}
	
	
	
	
	@Override
	public Integer saveCart(Cart cart) {
		return cartMapper.saveCart(cart);
	}
	
	@Override
	public Integer updateCart(Cart cart) {
		return cartMapper.updateCart(cart);
	}
	
	@Override
	public Integer removeCart(Cart cart) {
		return cartMapper.removeCart(cart);
	}
}
