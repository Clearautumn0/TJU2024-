package com.neusoft.elmboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.neusoft.elmboot.po.DeliveryAddress;

@Mapper
public interface DeliveryAddressMapper {

	@Select("select * from deliveryAddress where userId=#{userId} and delTag=1 order by daId")
	public List<DeliveryAddress> listDeliveryAddressByUserId(String userId);
	
	@Select("select * from deliveryAddress where daId=#{daId} and delTag=1")
	public DeliveryAddress getDeliveryAddressById(Integer daId);
	
	@Insert("insert into deliveryAddress values(null,#{contactName},#{contactSex},#{contactTel},#{address},#{userId})")
	public int saveDeliveryAddress(DeliveryAddress deliveryAddress);
	
	public int copyDeliveryAddress(DeliveryAddress deliveryAddress);
	
	@Delete("update deliveryAddress set delTag=0 where daId=#{daId}")
	public int deleteDeliveryAddress(Integer daId);
}
