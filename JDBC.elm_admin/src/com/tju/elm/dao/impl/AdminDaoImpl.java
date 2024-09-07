package com.tju.elm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tju.elm.dao.AdminDao;
import com.tju.elm.po.Admin;
import com.tju.elm.util.DBUtil;

public class AdminDaoImpl implements AdminDao{

	private Connection connection = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	
	
	@Override
	public Admin getAdminByNameByPass(String adminName, String password) {
		// TODO Auto-generated method stub
		Admin admin = null;
		//sql语句
		String sql = "select * from admin where adminName=? and password=?";
		try {
			connection =DBUtil.getConnection();
			pst = connection.prepareStatement(sql);
			pst.setString(1, adminName);
			pst.setString(2, password);
			rs = pst.executeQuery();
			//把数据库表结构数据转变为Java数据对象
			while (rs.next()) {
				admin = new Admin();
				admin.setAdminId(rs.getInt("adminId"));
				admin.setAdminName(rs.getString("adminName"));
				admin.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//释放资源
			DBUtil.close(rs, pst, connection);
		}
				
		return admin;
	}
}
