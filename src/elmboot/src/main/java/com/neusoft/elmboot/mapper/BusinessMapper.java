package com.neusoft.elmboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neusoft.elmboot.po.Business;

@Mapper
public interface BusinessMapper {

	@Select("select * from business where orderTypeId=#{orderTypeId} order by businessId")
	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId);
	
	@Select("select * from business where businessId=#{businessId}")
	public Business getBusinessById(Integer businessId);
	
	@Insert("insert into business values(null, #{businessName}, null, null, #{businessImg}, #{orderTypeId}, null, null, null)")
	public Integer registerBusiness(Business business);
	
	@Update("update business set businessName=#{businessName}, businessAddress=#{businessAddress}, businessExplain=#{businessExplain}, businessImg=#{businessImg}"
			+ "orderTypeId=#{orderTypeId}, starPrice=#{starPrice}, deliveryPrice=#{deliveryPrice}, remarks=#{remarks} where businessId=#{businessId}")
	public Integer updateBusiness(Business business);
	
	@Update("update business set delTag=0 where businessId=#{businessId}")
	public Integer deleteBusiness(Integer businessId);
}

