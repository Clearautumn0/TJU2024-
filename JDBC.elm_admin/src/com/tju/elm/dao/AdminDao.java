package com.tju.elm.dao;

import com.tju.elm.po.Admin;

public interface AdminDao {

	public Admin getAdminByNameByPass(String adminName,String password);
}
