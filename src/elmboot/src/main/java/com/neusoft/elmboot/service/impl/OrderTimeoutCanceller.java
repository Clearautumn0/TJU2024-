package com.neusoft.elmboot.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.neusoft.elmboot.po.Orders;
import com.neusoft.elmboot.service.OrdersService;

@Component
public class OrderTimeoutCanceller {

    @Resource
    private OrdersService ordersService;

    @Scheduled(cron = "0 * * * * *")	//定时任务,在每分钟的第0秒执行一次
    public void cancelTimeoutOrders() {
        // 获取当前时间，并指定时区为中国标准时间（Asia/Shanghai）。
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
    	// 定义日期时间格式
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        // 调用orderService的方法查询所有未支付的订单。
        List<Orders> unpaidOrders = ordersService.findUnpaidOrders();
        // 遍历未支付订单列表，对每个订单进行检查。
        for (Orders order : unpaidOrders) {

        	String orderDateStr = order.getOrderDate();
        	// 将字符串转换为 LocalDateTime 对象
        	LocalDateTime orderDateTime = LocalDateTime.parse(orderDateStr, formatter);

        	// 判断是否超时15分钟
        	if (now.isAfter(orderDateTime.plusMinutes(15))) {
        	    ordersService.cancelOrder(order.getOrderId());
        	}
        }
    }

}
