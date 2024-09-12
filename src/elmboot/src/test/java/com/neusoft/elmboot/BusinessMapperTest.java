package com.neusoft.elmboot;

import com.neusoft.elmboot.mapper.BusinessMapper;
import com.neusoft.elmboot.po.Business;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class BusinessMapperTest {

    @Autowired
    private SqlSession sqlSession;

    @MockBean
    private BusinessMapper businessMapper;

    @Test
    public void testRegisterBusiness() {
        Business business = new Business();
        business.setBusinessName("name");
        business.setBusinessAddress("addr");
        business.setBusinessExplain("explain");
        business.setBusinessImg("img");
        business.setOrderTypeId(1);
        business.setStarPrice(1.1);
        business.setDeliveryPrice(2.1);
        business.setRemarks("remark");
        business.setBusinessId(1);
        when(businessMapper.registerBusiness(business)).thenReturn(1);

        Integer result = sqlSession.insert("com.neusoft.elmboot.mapper.BusinessMapper.registerBusiness", business);
        assertEquals(1, result);
        verify(businessMapper).registerBusiness(business);
    }
    
    @Test
    public void testListBusinessByOrderTypeId() {
        Integer orderTypeId = 1;
        Business test1 = new Business(), test2 = new Business();
        test1.setOrderTypeId(orderTypeId);
        test2.setOrderTypeId(orderTypeId);
        List<Business> businesses = Arrays.asList(test1, test2);
        when(businessMapper.listBusinessByOrderTypeId(orderTypeId)).thenReturn(businesses);

        List<Business> result = sqlSession.selectList("com.neusoft.elmboot.mapper.BusinessMapper.listBusinessByOrderTypeId", orderTypeId);
        assertAll(
            () -> assertNotNull(result),
            () -> assertEquals(businesses, result)
        );
        verify(businessMapper).listBusinessByOrderTypeId(orderTypeId);
    }

    @Test
    public void testGetBusinessById() {
        Integer businessId = 1;
        Business business = new Business();
        when(businessMapper.getBusinessById(businessId)).thenReturn(business);

        Business result = sqlSession.selectOne("com.neusoft.elmboot.mapper.BusinessMapper.getBusinessById", businessId);
        assertAll(
            () -> assertNotNull(result),
            () -> assertEquals(business, result)
        );
        verify(businessMapper).getBusinessById(businessId);
    }

    @Test
    public void testUpdateBusiness() {
        Business business = new Business();
        business.setBusinessName("name");
        business.setBusinessAddress("addr");
        business.setBusinessExplain("explain");
        business.setBusinessImg("img");
        business.setOrderTypeId(1);
        business.setStarPrice(1.1);
        business.setDeliveryPrice(2.1);
        business.setRemarks("remark");
        business.setBusinessId(1);
        when(businessMapper.updateBusiness(business)).thenReturn(1);

        Integer result = sqlSession.update("com.neusoft.elmboot.mapper.BusinessMapper.updateBusiness", business);
        assertEquals(1, result);
        verify(businessMapper).updateBusiness(business);
    }

    @Test
    public void testDeleteBusiness() {
        Integer businessId = 1;
        when(businessMapper.deleteBusiness(businessId)).thenReturn(1);

        Integer result = sqlSession.update("com.neusoft.elmboot.mapper.BusinessMapper.deleteBusiness", businessId);
        assertEquals(1, result);
        verify(businessMapper).deleteBusiness(businessId);
    }
}
