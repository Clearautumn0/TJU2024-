package com.neusoft.elmboot.service;

import java.util.List;

import com.neusoft.elmboot.po.Orders;

public interface OrdersService {

	public Integer createOrders(Orders orders);
	public Orders getOrdersById(Integer orderId);
	public List<Orders> listOrdersByUserId(String userId);
	public List<Orders> findUnpaidOrders();
	public Integer cancelOrder(Integer orderId);
	public Integer setOrders(Integer orders);
}
