package com.neusoft.elmboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neusoft.elmboot.po.Food;

@Mapper
public interface FoodMapper {

	@Select("select * from food where businessId=#{businessId} order by foodId")
	public List<Food> listFoodByBusinessId(Integer businessId);
	
	@Select("select * from food where foodId=#{foodId} and delTag=1")
	public Food getFoodById(Integer foodId);
	
	@Insert("insert into food(foodName, foodExplain, foodImg, foodPrice, businessId, remarks) values(#{foodName}, #{foodExplain}, #{foodImg}, #{foodPrice}, #{businessId}, #{remarks})")
	@Options(useGeneratedKeys=true,keyProperty="foodId",keyColumn="foodId")
	public Integer addFood(Food food);
	
	
//	public Integer copyFood(Food food);
	
//	@Update("update food set delTag=0 where businessId=#{businessId} and foodId=#{foodId}")
//	public Integer deleteFood(Food food);
}
