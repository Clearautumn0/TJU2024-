package com.tju.elm.dao;


import java.util.List;

import com.tju.elm.po.Business;

public interface BusinessDao {

	public List<Business> listBusiness(String businessName,String businessAddress);
	public int saveBusiness(String businessName);
	public int removeBusiness(int businessId);
	
	public Business getBusinessByIdByPass(int businessId, String password);
	public Business getBusinessById(int businessId);
	public int updateBusiness(Business business);
	public int updateBusinessByPassword(int businessId,String password);
	
}
