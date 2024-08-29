package com.neusoft.elmboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.po.Food;
import com.neusoft.elmboot.service.FoodService;

@RestController
@RequestMapping("/FoodController")
public class FoodController {
	
	@Autowired
	private FoodService foodService;

	//@RequestMapping("/listFoodByBusinessId")
	@GetMapping("/business/{businessId}")
	public List<Food> listFoodByBusinessId(@PathVariable Integer businessId) throws Exception{
		return foodService.listFoodByBusinessId(businessId);
	}
	
	@GetMapping("/{foodId}")
	public Food getFoodById(@PathVariable Integer foodId) throws Exception{
		return foodService.getFoodById(foodId);
	}
	
	@PostMapping
	public Integer addFood(@RequestBody Food food) {
		return foodService.addFood(food);
	}
	
	@PutMapping
	public Integer updateFood(@RequestBody Food food) {
		return foodService.updateFood(food);
	}
	
	@DeleteMapping
	public Integer removeFood(Food food) {
		return foodService.removeFood(food);
	}
}
