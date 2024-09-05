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

//	@Override
//	public User getUserByIdByPass(String userId, String password) {
//		User user = userMapper.getUserById(userId);
//		return userMapper.getUserByIdByPass(user);
//	}
	
	@Override
	public User getUserByIdByPass(User user) {

	    User reuser = userMapper.getUserByIdByPass(user);
	    if (reuser != null) {
	        reuser.setPassword("");//返回时不返回用户的密码
	    }
	    return reuser; // 这里返回null也是安全的，因为已经做了非空检查

	}
	
//	@Override
//	public User getUserByIdByPass(User user) {
//	    return userMapper.getUserByIdByPass(user);
//	}
	
	@Override
	public int getUserById(String userId) {
		return userMapper.getUserById(userId);
	}
	
	@Override
	public int saveUser(User user) {
		//对前端传入的明文密码进行加密
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		return userMapper.saveUser(user);
	}
	
//	@Override
//	public int saveUser(User user) {
//		return userMapper.saveUser(user);
//	}

	@Override
	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}

	@Override
	public int deleteUser(User user) {
		return userMapper.deleteUser(user);
	}
}
