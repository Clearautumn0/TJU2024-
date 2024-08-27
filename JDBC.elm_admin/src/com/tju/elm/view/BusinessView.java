package com.tju.elm.view;

import com.tju.elm.po.Business;

public interface BusinessView {

	public void listBusinessAll();
	public void listBusiness();
	public void saveBusiness();
	public void removeBusiness();
	
	public Business login();
	
}
