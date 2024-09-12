package com.neusoft.elmboot.service;

import java.util.List;

import com.neusoft.elmboot.po.DeliveryAddress;

public interface DeliveryAddressService {

	public List<DeliveryAddress> listDeliveryAddressByUserId(String userId);
	
	public DeliveryAddress getDeliveryAddressById(Integer daId);
	public Integer saveDeliveryAddress(DeliveryAddress deliveryAddress);
	public Integer updateDeliveryAddress(DeliveryAddress deliveryAddress);
	public Integer removeDeliveryAddress(Integer daId);
}
