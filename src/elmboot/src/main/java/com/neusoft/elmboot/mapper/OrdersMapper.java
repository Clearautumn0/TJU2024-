package com.neusoft.elmboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.neusoft.elmboot.po.Orders;

@Mapper
public interface OrdersMapper {

	@Insert("insert into orders(userId,businessId,orderDate,orderTotal,daId,orderState,createTime) "
			+ "values(#{userId},#{businessId},#{orderDate},#{orderTotal},#{daId},0,#{createTime})")
	@Options(useGeneratedKeys=true,keyProperty="orderId",keyColumn="orderId")
	public int saveOrders(Orders orders);
	
	public Orders getOrdersById(Integer orderId);
	public List<Orders> listOrdersByUserId(String userId);
	
	@Select("select * from orders where orderState=0")
	public List<Orders> findUnpaidOrders();
	
	@Delete("delete from orders where orderId=#{orderId}")
	public int cancelOrder(int orderId);
}
