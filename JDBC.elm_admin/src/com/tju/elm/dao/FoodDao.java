package com.tju.elm.dao;

import java.util.List;

import com.tju.elm.po.Food;

public interface FoodDao {

	public List<Food> listFoodByBusinessId(int businessId);
	public int saveFood(Food food);
	public Food getFoodById(int foodId);
	public int updatefoodId(Food food);
	public int removeFood(int foodId);
}
