package com.neusoft.elmboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.elmboot.mapper.BusinessMapper;
import com.neusoft.elmboot.mapper.UserMapper;
import com.neusoft.elmboot.po.Business;
import com.neusoft.elmboot.service.BusinessService;

@Service
public class BusinessServiceImpl implements BusinessService{
	
	@Autowired
	private BusinessMapper businessMapper;
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) {
		return businessMapper.listBusinessByOrderTypeId(orderTypeId);
	}
	
	@Override
	public Business getBusinessById(Integer businessId) {
		return businessMapper.getBusinessById(businessId);
	}

	@Override
	@Transactional
	public Integer registerBusiness(Business business, String userId) {
		int count = businessMapper.registerBusiness(business);
		count += userMapper.updateUserBusinessId(business.getBusinessId(), userId);
		return count;
	}

	@Override
	public Integer updateBusiness(Business business) {
		return businessMapper.updateBusiness(business);
	}
	
	@Override
	@Transactional
	public Integer removeBusiness(Integer businessId, String userId) {
		int count = businessMapper.deleteBusiness(businessId);
		count += userMapper.updateUserBusinessId(businessId, userId);
		return count;
	}
}
