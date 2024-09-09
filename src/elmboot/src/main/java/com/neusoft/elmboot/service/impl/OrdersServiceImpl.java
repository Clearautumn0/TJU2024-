package com.neusoft.elmboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.elmboot.mapper.CartMapper;
import com.neusoft.elmboot.mapper.OrderDetailetMapper;
import com.neusoft.elmboot.mapper.OrdersMapper;
import com.neusoft.elmboot.po.Cart;
import com.neusoft.elmboot.po.OrderDetailet;
import com.neusoft.elmboot.po.Orders;
import com.neusoft.elmboot.service.OrdersService;
import com.neusoft.elmboot.util.CommonUtil;

@Service
public class OrdersServiceImpl implements OrdersService{
	
	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private OrderDetailetMapper orderDetailetMapper;

	@Override
	@Transactional
	public int createOrders(Orders orders) {
	    // 1. 查询当前用户购物车中当前商家的所有食品
	    Cart cart = new Cart();
	    cart.setUserId(orders.getUserId());
	    cart.setBusinessId(orders.getBusinessId());
	    List<Cart> cartList = cartMapper.listCart(cart);

	    // 添加购物车是否为空的判断
	    if (cartList == null || cartList.isEmpty()) {
	        throw new IllegalArgumentException("购物车为空，无法生成订单");
	    }

	    // 2. 创建订单（返回生成的订单编号）
	    orders.setOrderDate(CommonUtil.getCurrentDate());
	    ordersMapper.saveOrders(orders);
	    int orderId = orders.getOrderId();

	    // 3. 批量添加订单明细
	    List<OrderDetailet> orderDetailetList = new ArrayList<>();
	    for (Cart c : cartList) {
	        OrderDetailet od = new OrderDetailet();
	        od.setOrderId(orderId);
	        od.setFoodId(c.getFoodId());
	        od.setQuantity(c.getQuantity());
	        orderDetailetList.add(od);
	    }

	    // 添加订单明细是否为空的判断
	    if (orderDetailetList.isEmpty()) {
	        throw new IllegalArgumentException("订单明细为空，无法生成订单");
	    }

	    // 保存订单明细
	    orderDetailetMapper.saveOrderDetailetBatch(orderDetailetList);

	    // 4. 从购物车表中删除相关食品信息
	    cartMapper.removeCart(cart);

	    return orderId;
	}
	
	@Override
	public Orders getOrdersById(Integer orderId) {
		return ordersMapper.getOrdersById(orderId);
	}
	
	@Override
	public List<Orders> listOrdersByUserId(String userId){
		return ordersMapper.listOrdersByUserId(userId);
	}

	@Override
	public List<Orders> findUnpaidOrders() {
		return ordersMapper.findUnpaidOrders();
	}

	@Override
	public int cancelOrder(Integer orderId) {
		return ordersMapper.cancelOrder(orderId);
	}
}
