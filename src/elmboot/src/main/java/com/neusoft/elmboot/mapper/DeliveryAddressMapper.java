package com.neusoft.elmboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neusoft.elmboot.po.DeliveryAddress;

@Mapper
public interface DeliveryAddressMapper {

	@Select("select * from deliveryAddress where userId=#{userId} and delTag=0 order by daId")
	public List<DeliveryAddress> listDeliveryAddressByUserId(String userId);
	
	@Select("select * from deliveryAddress where daId=#{daId} and delTag=0")
	public DeliveryAddress getDeliveryAddressById(Integer daId);
	
	@Insert("insert into deliveryAddress values(null,#{contactName},#{contactSex},#{contactTel},#{address},#{userId}, 0)")
	public Integer saveDeliveryAddress(DeliveryAddress deliveryAddress);
	
	@Insert("insert into deliveryAddress values (null, #{contactName}, #{contactSex}, #{contactTel}, #{address}, #{userId}, 0)")
	public Integer copyDeliveryAddress(DeliveryAddress deliveryAddress);
	
	@Update("update deliveryAddress set delTag=1 where daId=#{daId}")
	public Integer deleteDeliveryAddress(Integer daId);
}
