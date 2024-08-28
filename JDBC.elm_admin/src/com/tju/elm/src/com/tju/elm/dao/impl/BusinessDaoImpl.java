package com.tju.elm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tju.elm.dao.BusinessDao;
import com.tju.elm.po.Admin;
import com.tju.elm.po.Business;
import com.tju.elm.util.DBUtil;

public class BusinessDaoImpl implements BusinessDao{

	private Connection connection = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	
	@Override
	public List<Business> listBusiness(String businessName,String businessAddress) {
		// TODO Auto-generated method stub
		List<Business> list = new ArrayList<>();
		//sql语句
		StringBuffer sql = new StringBuffer("select * from business where 1=1");
		if(businessName!=null&&!businessName.equals("")) {
			sql.append(" and businessName like '%"+businessName+"%'");
		}
		if(businessAddress!=null&&!businessAddress.equals("")) {
			sql.append(" and businessAddress like '%"+businessAddress+"%'");
		}
		try {
			connection =DBUtil.getConnection();
			pst = connection.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			//把数据库表结构数据转变为Java数据对象
			while (rs.next()) {
				Business business = new Business();
				business.setBusinessId(rs.getInt("businessId"));
				business.setPassword(rs.getString("password"));
				business.setBusinessName(rs.getString("businessName"));
				business.setBusinessAddress(rs.getString("businessAddress"));
				business.setBusinessExplain(rs.getString("businessExplain"));
				business.setStarPrice(rs.getDouble("starPrice"));
				business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
				list.add(business);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//释放资源
			DBUtil.close(rs, pst, connection);
		}
		return list;
	}
	
	@Override
	public int saveBusiness(String businessName) {
		// TODO Auto-generated method stub
		int businessId = 0;
		//sql语句
		String sql = "insert into business(businessName,password) values(?,'123')";
		try {
			connection =DBUtil.getConnection();
			pst = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);//设置返回自增长列值
			pst.setString(1, businessName);
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();//获取自增长列值（一行一列）
			if(rs.next()) {
				businessId = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//释放资源
			DBUtil.close(rs, pst, connection);
		}
		return businessId;
	}
	
	public int removeBusiness(int businessId) {
		// TODO Auto-generated method stub
		int result = 0;
		//sql语句
		String delfootSql = "delete from food where businessId=?";
		String delBusinessSql = "delete from business where businessId=?";
		try {
			connection =DBUtil.getConnection();
			//开启一个事务
			connection.setAutoCommit(false);
			
			pst = connection.prepareStatement(delfootSql);
			pst.setInt(1, businessId);
			pst.executeUpdate();
			
			pst = connection.prepareStatement(delBusinessSql);
			pst.setInt(1, businessId);
			result = pst.executeUpdate();
			
			connection.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = 0;
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			//释放资源
			DBUtil.close(null, pst, connection);
		}
		return result;
	}

	
	public Business getBusinessByIdByPass(int businessId, String password) {
		// TODO Auto-generated method stub
		Business business = null;
		//sql语句
		String sql = "select * from business where businessId=? and password=?";
		try {
			connection =DBUtil.getConnection();
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, businessId);
			pst.setString(2, password);
			rs = pst.executeQuery();
			//把数据库表结构数据转变为Java数据对象
			while (rs.next()) {
				business = new Business();
				business.setBusinessId(rs.getInt("businessId"));
				business.setPassword(rs.getString("password"));
				business.setBusinessName(rs.getString("businessName"));
				business.setBusinessAddress(rs.getString("businessAddress"));
				business.setBusinessExplain(rs.getString("businessExplain"));
				business.setStarPrice(rs.getDouble("starPrice"));
				business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//释放资源
			DBUtil.close(rs, pst, connection);
		}
		return business;
	}
	
	@Override
	public Business getBusinessById(int businessId) {
		// TODO Auto-generated method stub
		Business business = null;
		//sql语句
		String sql = "select * from business where businessId=?";
		try {
			connection =DBUtil.getConnection();
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, businessId);
			rs = pst.executeQuery();
			//把数据库表结构数据转变为Java数据对象
			while (rs.next()) {
				business = new Business();
				business.setBusinessId(rs.getInt("businessId"));
				business.setPassword(rs.getString("password"));
				business.setBusinessName(rs.getString("businessName"));
				business.setBusinessAddress(rs.getString("businessAddress"));
				business.setBusinessExplain(rs.getString("businessExplain"));
				business.setStarPrice(rs.getDouble("starPrice"));
				business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//释放资源
			DBUtil.close(rs, pst, connection);
		}
		return business;
	}
	
	@Override
	public int updateBusiness(Business business) {
		// TODO Auto-generated method stub
		int result = 0;
		//sql语句
		String sql = "update business set businessName=?,businessAddress=?,businessExplain=?,starPrice=?,deliveryPrice=? where businessId=?";
		try {
			connection =DBUtil.getConnection();
			pst = connection.prepareStatement(sql);
			pst.setString(1, business.getBusinessName());
			pst.setString(2, business.getBusinessAddress());
			pst.setString(3, business.getBusinessExplain());
			pst.setDouble(4, business.getStarPrice());
			pst.setDouble(5, business.getDeliveryPrice());
			pst.setInt(6, business.getBusinessId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//释放资源
			DBUtil.close(null, pst, connection);
		}
		return result;
	}
	
	@Override
	public int updateBusinessByPassword(int businessId, String password) {
		// TODO Auto-generated method stub
		int result = 0;
		//sql语句
		String sql = "update business set password=? where businessId=?";
		try {
			connection =DBUtil.getConnection();
			pst = connection.prepareStatement(sql);
			pst.setString(1, password);
			pst.setInt(2, businessId);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//释放资源
			DBUtil.close(null, pst, connection);
		}
		return result;
	}
	
}
