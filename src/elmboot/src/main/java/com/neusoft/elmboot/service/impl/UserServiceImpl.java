package com.neusoft.elmboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.elmboot.mapper.UserMapper;
import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUserByIdByPass(User user) {
		User reuser = new User();
		reuser = userMapper.getUserByIdByPass(user);
		reuser.setPassword(null);
		return reuser;
	}
	
	@Override
	public int getUserById(String userId) {
		
		
		return userMapper.getUserById(userId);
	}
	
	@Override
	public int saveUser(User user) {
		return userMapper.saveUser(user);
	}

	@Override
	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}

	@Override
	public int deleteUser(User user) {
		return userMapper.deleteUser(user);
	}
}
