package com.neusoft.elmboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.elmboot.mapper.FoodMapper;
import com.neusoft.elmboot.po.Food;
import com.neusoft.elmboot.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService{
	
	@Autowired
	private FoodMapper foodMapper;

	@Override
	public List<Food> listFoodByBusinessId(Integer businessId) {
		return foodMapper.listFoodByBusinessId(businessId);
	}
	
	@Override
	public Food getFoodById(Integer foodId) {
		return foodMapper.getFoodById(foodId);
	}

	@Override
	public Integer addFood(Food food) {
		return foodMapper.addFood(food);
	}

	@Override
	public Integer updateFood(Food food) {
		return foodMapper.updateFood(food);
	}
	
	@Override
	public Integer removeFood(Food food) {
		return foodMapper.removeFood(food);
	}

}
