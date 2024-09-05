package com.neusoft.elmboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.neusoft.elmboot.mapper.UserMapper;
import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

//	@Override
//	public User getUserByIdByPass(String userId, String password) {
//		User user = userMapper.getUserById(userId);
//		return userMapper.getUserByIdByPass(user);
//	}
	
	@Override
	public User getUserByIdByPass(User user) {
		User storedUser = userMapper.getUserById(user.getUserId());
		// 无对应用户
		if(storedUser == null) {
			return null;
		}
		// 密码不符
		if(!passwordEncoder.matches(user.getPassword(), storedUser.getPassword())) {
			return null;
		}
		storedUser.setPassword("");
		return storedUser;
	}
	
	@Override
	public int getUserById(String userId) {
		// 正则表达式验证手机号格式
	    String regex = "^1[3-9]\\d{9}$";
	    if (!userId.matches(regex)) {
	        // 如果手机号格式不正确，抛出异常或返回错误信息
	        //throw new IllegalArgumentException("Invalid phone number format for userId: " + userId);
	    	return 0;
	    }
	    // 无对应用户
		User user = userMapper.getUserById(userId);
		if(user!=null) {
			return 1;
		}
		return 0;
	}
	
	@Override
	public int saveUser(User user) {
		// 对前端传入的明文密码进行加密
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		return userMapper.saveUser(user);
	}

	@Override
	public int updateUser(User user) {
		User storedUser = userMapper.getUserById(user.getUserId());
		// 无对应用户
		if(storedUser == null) {
			return 0;
		}
		// 密码不符
		if(!passwordEncoder.matches(user.getPassword(), storedUser.getPassword())) {
			return 0;
		}
		return userMapper.updateUser(user);
	}

	@Override
	public int deleteUser(User user) {
		User storedUser = userMapper.getUserById(user.getUserId());
		// 无对应用户
		if(storedUser == null) {
			return 0;
		}
		// 密码不符
		if(!passwordEncoder.matches(user.getPassword(), storedUser.getPassword())) {
			return 0;
		}
		return userMapper.deleteUser(user);
	}


}
