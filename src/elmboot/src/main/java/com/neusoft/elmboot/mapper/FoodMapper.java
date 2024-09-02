package com.neusoft.elmboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neusoft.elmboot.po.Food;

@Mapper
public interface FoodMapper {

	@Select("select * from food where businessId=#{businessId} order by foodId")
	public List<Food> listFoodByBusinessId(Integer businessId);
	
	@Select("select * from food where foodId=#{foodId}")
	public Food getFoodById(Integer foodId);
	
	@Insert("insert into food values(null, #{foodName}, #{foodExplain}, #{foodImg}, #{foodPrice}, #{businessId}, #{remarks}, 1)")
	public Integer addFood(Food food);
	
	//@Update("update food set foodName=#{foodName},foodExplain=#{foodExplain},foodImg=#{foodImg},foodPrice=#{foodPrice},remarks=#{remarks},quantity=#{quantity} where businessId=#{businessId} and foodId=#{foodId}")
	//public Integer updateFood(Food food);
	
	//@Update("update food set delTag=0 where businessId=#{businessId} and foodId=#{foodId}")
	//public Integer removeFood(Food food);
}
