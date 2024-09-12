package com.neusoft.elmboot;

import com.neusoft.elmboot.mapper.BusinessMapper;
import com.neusoft.elmboot.mapper.UserMapper;
import com.neusoft.elmboot.po.Business;
import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.impl.BusinessServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BusinessServiceImplTest {

    @Autowired
    private BusinessServiceImpl businessService;

    @MockBean
    private BusinessMapper businessMapper;

    @MockBean
    private UserMapper userMapper;

    @Test
    public void testListBusinessByOrderTypeId() {
        Integer orderTypeId = 1;
        Business test1 = new Business(), test2 = new Business();
        test1.setOrderTypeId(orderTypeId);
        test2.setOrderTypeId(orderTypeId);
        List<Business> businesses = Arrays.asList(test1, test2);
        when(businessMapper.listBusinessByOrderTypeId(orderTypeId)).thenReturn(businesses);

        List<Business> result = businessService.listBusinessByOrderTypeId(orderTypeId);
        assertNotNull(result);
        assertEquals(businesses, result);
        verify(businessMapper).listBusinessByOrderTypeId(orderTypeId);
    }

    @Test
    public void testGetBusinessById() {
        Integer businessId = 1;
        Business business = new Business();
        when(businessMapper.getBusinessById(businessId)).thenReturn(business);

        Business result = businessService.getBusinessById(businessId);
        assertNotNull(result);
        assertEquals(business, result);
        verify(businessMapper).getBusinessById(businessId);
    }

    @Test
    @Transactional
    public void testRegisterBusiness() {
        String userId = "user123";
        Business business = new Business();
        when(businessMapper.registerBusiness(business)).thenReturn(1);
        when(userMapper.updateUserBusinessId(anyInt(), anyString())).thenReturn(1);
        when(userMapper.updateAuthorization(any(User.class))).thenReturn(1);

        Integer result = businessService.registerBusiness(business, userId);
        assertEquals(2, result);
        verify(businessMapper).registerBusiness(business);
        verify(userMapper).updateUserBusinessId(anyInt(), anyString());
        verify(userMapper).updateAuthorization(any(User.class));
    }

    @Test
    public void testUpdateBusiness() {
        Business business = new Business();
        when(businessMapper.updateBusiness(business)).thenReturn(1);

        Integer result = businessService.updateBusiness(business);
        assertEquals(1, result);
        verify(businessMapper).updateBusiness(business);
    }

    @Test
    @Transactional
    public void testRemoveBusiness() {
        Integer businessId = 1;
        String userId = "user123";
        when(businessMapper.deleteBusiness(businessId)).thenReturn(1);
        when(userMapper.updateUserBusinessId(anyInt(), anyString())).thenReturn(1);

        Integer result = businessService.removeBusiness(businessId, userId);
        assertEquals(2, result);
        verify(businessMapper).deleteBusiness(businessId);
        verify(userMapper).updateUserBusinessId(anyInt(), anyString());
    }
}
