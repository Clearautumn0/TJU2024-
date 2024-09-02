package com.neusoft.elmboot;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.neusoft.elmboot.po.Food;
import com.neusoft.elmboot.service.FoodService;
import com.neusoft.elmboot.service.impl.FoodServiceImpl;

@SpringBootTest
public class FoodServiceTest {

	@Autowired
    private FoodService foodService;

    @MockBean
    private FoodServiceImpl foodServiceImpl;

    @Test
    void testAddFood() {
        // 准备测试数据
        
        Food food1 = new Food();	//add food1
        Food food2 = new Food();	//update food1
        Food food3 = new Food();	//delete food2
        
        food1.setFoodName("food1");
        food1.setFoodExplain("test add");
        food1.setFoodImg("img1");
        food1.setFoodPrice(1.00);
        food1.setBusinessId(10003);
        
        food2.setFoodName("food2");
        food2.setFoodExplain("test update");
        food2.setFoodImg("img2");
        food2.setFoodId(13);
        food2.setFoodPrice(2.00);
        food2.setBusinessId(10003);

        food3.setFoodName("food3");
        food3.setFoodExplain("test delete");
        food3.setFoodImg("img3");
        food3.setFoodPrice(3.00);
        food3.setBusinessId(10003);

        int expRes1 = 1;
        int expRes2 = 2;
        int expRes3 = 1;

        // 设置预期行为
        when(foodServiceImpl.addFood(food1)).thenReturn(expRes1);
 //       when(foodServiceImpl.removeFood(food2)).thenReturn(expRes3);

        // 执行操作
        int result1 = foodService.addFood(food1);
//        int result3 = foodService.removeFood(food2);

        // 验证结果
        assertEquals(result1, expRes1);
 //       assertEquals(result3, expRes3);
        

        // 验证交互
        verify(foodServiceImpl).addFood(food1);
//        verify(foodServiceImpl).removeFood(food2);
    }

}
