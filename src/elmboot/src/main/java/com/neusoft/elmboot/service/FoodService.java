package com.neusoft.elmboot.service;

import java.util.List;

import com.neusoft.elmboot.po.Food;

public interface FoodService {

	public List<Food> listFoodByBusinessId(Integer businessId);
	public Integer addFood(Food food);
	public Food getFoodById(Integer foodId);
	public Integer updateFood(Food food);
	public Integer removeFood(Food food);
}
