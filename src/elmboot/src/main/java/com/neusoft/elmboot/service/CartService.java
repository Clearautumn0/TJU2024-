package com.neusoft.elmboot.service;

import java.util.List;

import com.neusoft.elmboot.po.Cart;

public interface CartService {

	public List<Cart> listCart(String userId, Integer businessId);
	public Integer saveCart(Cart cart);
	public Integer updateCart(Cart cart);
	public Integer removeCart(Cart cart);
}
