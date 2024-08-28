package com.tju.elm.view;

import java.util.Scanner;

import com.tju.elm.dao.AdminDao;
import com.tju.elm.dao.impl.AdminDaoImpl;
import com.tju.elm.po.Admin;

public class AdminViewImpl implements AdminView{

	private Scanner input = new Scanner(System.in);
	
	@Override
	public Admin login() {
		// TODO Auto-generated method stub
		System.out.println("请输入管理员名称：");
		String adminName = input.next();
		System.out.println("请输入密码：");
		String password = input.next();
		
		AdminDao dao = new AdminDaoImpl();
		Admin admin = dao.getAdminByNameByPass(adminName, password);
		return admin;
	}
}
