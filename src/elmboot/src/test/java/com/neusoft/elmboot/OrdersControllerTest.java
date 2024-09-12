package com.neusoft.elmboot;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;

import com.neusoft.elmboot.controller.OrdersController;
import com.neusoft.elmboot.interceptor.TokenInterceptor;
import com.neusoft.elmboot.po.Orders;
import com.neusoft.elmboot.service.OrdersService;

@WebMvcTest(OrdersController.class)
public class OrdersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrdersService ordersService;

    // Mock the TokenInterceptor if necessary
    @MockBean
    private TokenInterceptor tokenInterceptor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrders() throws Exception {
        Orders order = new Orders();
        when(ordersService.createOrders(order)).thenReturn(1);

        mockMvc.perform(post("/orders")
               .contentType("application/json")
               .content("{\"orderId\":1}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.data").value(1));
    }

//    @Test
//    public void testListOrdersByUserId() throws Exception {
//        // Mocking the OrdersService response
//        Orders order1 = new Orders();
//        Orders order2 = new Orders();
//        List<Orders> ordersList = new ArrayList<>();
//        ordersList.add(order1);
//        ordersList.add(order2);
//        when(ordersService.listOrdersByUserId("userId")).thenReturn(ordersList);
//
//        mockMvc.perform(get("/orders/user/{userId}", "userId"))
//               .andExpect(status().isOk())
//               .andExpect(jsonPath("$[0]").exists())
//               .andExpect(jsonPath("$[1]").exists());
//    }
//
//    @Test
//    public void testGetOrdersById() throws Exception {
//        // Mocking the OrdersService response
//        Orders order = new Orders();
//        when(ordersService.getOrdersById(1)).thenReturn(order);
//
//        mockMvc.perform(get("/orders/{orderId}", 1))
//               .andExpect(status().isOk())
//               .andExpect(jsonPath("$").exists());
//    }
//
//
//    @Test
//    public void testSetOrder() throws Exception {
//        // Mocking the OrdersService response
//        when(ordersService.setOrders(1)).thenReturn(1);
//
//        mockMvc.perform(patch("/orders/{orderId}", 1))
//               .andExpect(status().isOk())
//               .andExpect(content().string("1"));
//    }
//
//    @Test
//    public void testCancelOrder() throws Exception {
//        // Mocking the OrdersService response
//        when(ordersService.cancelOrder(1)).thenReturn(1);
//
//        Map<String, Integer> expectedResponse = new HashMap<>();
//        expectedResponse.put("data", 1);
//
//        mockMvc.perform(delete("/orders/{orderId}", 1))
//               .andExpect(status().isOk())
//               .andExpect(jsonPath("$.data").value(1));
//    }
}

