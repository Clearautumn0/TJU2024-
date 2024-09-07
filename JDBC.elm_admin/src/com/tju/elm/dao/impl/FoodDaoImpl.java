package com.tju.elm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tju.elm.dao.FoodDao;
import com.tju.elm.po.Business;
import com.tju.elm.po.Food;
import com.tju.elm.util.DBUtil;

public class FoodDaoImpl implements FoodDao{

	private Connection connection = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	
	@Override
	public List<Food> listFoodByBusinessId(int businessId) {
		// TODO Auto-generated method stub
		List<Food> list = new ArrayList<>();
		//sql语句
		String sql = "select * from food where businessId=?";
		try {
			connection =DBUtil.getConnection();
			pst = connection.prepareStatement(sql);
			pst.setInt(1, businessId);
			rs = pst.executeQuery();
			//把数据库表结构数据转变为Java数据对象
			while (rs.next()) {
				Food food = new Food();
				food.setFoodId(rs.getInt("foodId"));
				food.setFoodName(rs.getString("foodName"));
				food.setFoodExplain(rs.getString("foodExplain"));
				food.setFoodPrice(rs.getDouble("foodPrice"));
				food.setBusinessId(rs.getInt("businessId"));
				list.add(food);
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
	
	public int saveFood(Food food) {
		// TODO Auto-generated method stub
		int result = 0;
		//sql语句
		String sql = "insert into food values(null,?,?,?,?)";
		try {
			connection =DBUtil.getConnection();
			pst = connection.prepareStatement(sql);
			pst.setString(1, food.getFoodName());
			pst.setString(2, food.getFoodExplain());
			pst.setDouble(3, food.getFoodPrice());
			pst.setInt(4, food.getBusinessId());
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
	public Food getFoodById(int foodId) {
		// TODO Auto-generated method stub
		Food food = null;
		//sql语句
		String sql = "select * from food where foodId=?";
		try {
			connection =DBUtil.getConnection();
			pst = connection.prepareStatement(sql);
			pst.setInt(1, foodId);
			rs = pst.executeQuery();
			//把数据库表结构数据转变为Java数据对象
			while (rs.next()) {
				food = new Food();
				food.setFoodId(rs.getInt("foodId"));
				food.setFoodName(rs.getString("foodName"));
				food.setFoodExplain(rs.getString("foodExplain"));
				food.setFoodPrice(rs.getDouble("foodPrice"));
				food.setBusinessId(rs.getInt("businessId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//释放资源
			DBUtil.close(rs, pst, connection);
		}
		return food;
	}
	
	@Override
	public int updatefoodId(Food food) {
		// TODO Auto-generated method stub
		int result = 0;
		//sql语句
		String sql = "update food set foodName=?,foodExplain=?,foodPrice=? where foodId=?";
		try {
			connection =DBUtil.getConnection();
			pst = connection.prepareStatement(sql);
			pst.setString(1, food.getFoodName());
			pst.setString(2, food.getFoodExplain());
			pst.setDouble(3, food.getFoodPrice());
			pst.setInt(4, food.getFoodId());
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
	public int removeFood(int foodId) {
		// TODO Auto-generated method stub
		int result = 0;
		//sql语句
		String sql = "delete from food where foodId=?";
		try {
			connection =DBUtil.getConnection();
			pst = connection.prepareStatement(sql);
			pst.setInt(1, foodId);
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
