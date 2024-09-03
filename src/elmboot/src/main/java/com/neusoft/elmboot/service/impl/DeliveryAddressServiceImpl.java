package com.neusoft.elmboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.elmboot.mapper.DeliveryAddressMapper;
import com.neusoft.elmboot.po.DeliveryAddress;
import com.neusoft.elmboot.service.DeliveryAddressService;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService{
	
	@Autowired
	private DeliveryAddressMapper deliveryAddressMapper;
	
	@Override
	public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) {
		return deliveryAddressMapper.listDeliveryAddressByUserId(userId);
	}
	
	@Override
	public DeliveryAddress getDeliveryAddressById(Integer daId) {
		return deliveryAddressMapper.getDeliveryAddressById(daId);
	}
	
	@Override
	public int saveDeliveryAddress(DeliveryAddress deliveryAddress) {
		return deliveryAddressMapper.saveDeliveryAddress(deliveryAddress);
	}
	
	@Override
	@Transactional
	public int updateDeliveryAddress(DeliveryAddress deliveryAddress) {
		int count = deliveryAddressMapper.deleteDeliveryAddress(deliveryAddress.getDaId());
		count+=deliveryAddressMapper.copyDeliveryAddress(deliveryAddress);
		return count;
	}
	
	@Override
	public int removeDeliveryAddress(Integer daId) {
		return deliveryAddressMapper.deleteDeliveryAddress(daId);
	}
}
