package com.neusoft.elmboot.service;

import com.neusoft.elmboot.po.User;

public interface UserService {

	public User getUserByIdByPass(User user) throws Exception;
	public Integer getUserById(String userId);
	public Integer saveUser(User user);
	public Integer updateUser(User user);
	public Integer deleteUser(User user);
	public Integer updateAuthorization(User user);
	public Integer getBusinessIdById(String userId);
}
