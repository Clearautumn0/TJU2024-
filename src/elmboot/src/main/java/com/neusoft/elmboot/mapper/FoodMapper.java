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

	@Select("select * from food where businessId=#{businessId}  and delTag=0 and soldOut =0 order by foodId")
	public List<Food> listFoodByBusinessId(Integer businessId);
	
	@Select("select * from food where foodId=#{foodId} and and soldOut=0 delTag=0")
	public Food getFoodById(Integer foodId);
	
	@Insert("insert into food values(null, #{foodName}, #{foodExplain}, #{foodImg}, #{foodPrice}, #{businessId}, #{remarks},0, 0)")
	public Integer addFood(Food food);
	
	@Insert("insert into food values (null, #{foodName}, #{foodExplain}, #{foodImg}, #{foodPrice}, #{businessId}, #{remarks}, 0,0)")
	public Integer copyFood(Food food);
	
	//设置商品是否告罄
	@Update("update food set soldOut=#{soldOut} where businessId=#{businessId} and foodId=#{foodId}")
	public Integer setFood(Food food);

	@Update("update food set delTag=1 where businessId=#{businessId} and foodId=#{foodId}")
	public Integer deleteFood(Food food);
}
