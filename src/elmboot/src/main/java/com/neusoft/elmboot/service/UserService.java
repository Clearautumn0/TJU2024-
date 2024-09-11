package com.neusoft.elmboot.service;

import com.neusoft.elmboot.po.User;

public interface UserService {

	public User getUserByIdByPass(User user) throws Exception;
	public int getUserById(String userId);
	public int saveUser(User user);
	public int updateUser(User user);
	public int deleteUser(User user);
	public int updateAuthorization(User user);
	public int getBusinessIdById(String userId);
}
