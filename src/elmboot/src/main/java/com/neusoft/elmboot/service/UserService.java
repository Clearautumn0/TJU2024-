package com.neusoft.elmboot.service;

import com.neusoft.elmboot.po.User;

public interface UserService {

	public User getUserByIdByPass(String userId, String password);
	public int getUserById(String userId);
	public int saveUser(User user);
	public int updateUser(User user);
	public int deleteUser(User user);
}
