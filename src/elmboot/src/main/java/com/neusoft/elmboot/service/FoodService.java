package com.neusoft.elmboot.service;

import java.util.List;

import com.neusoft.elmboot.po.Food;

public interface FoodService {

	public List<Food> listFoodByBusinessId(Integer businessId);
	public Food getFoodById(Integer foodId);
	public Integer addFood(Food food);
	public Integer updateFood(Food food);
	public Integer setFood(Food food);
	public Integer removeFood(Food food);
}
