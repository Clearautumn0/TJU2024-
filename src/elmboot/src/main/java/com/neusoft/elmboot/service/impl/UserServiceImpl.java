package com.neusoft.elmboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.neusoft.elmboot.mapper.UserMapper;
import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.UserService;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import com.neusoft.elmboot.util.RsaUtil;



import com.neusoft.elmboot.util.TokenUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
    @Value("${security.rsa.private-key}")
    private String privateKeyStr;  // 从配置文件或安全存储中获取私钥

	@Override
	public User getUserByIdByPass(User user) throws Exception {
		User storedUser = userMapper.getUserById(user.getUserId());
		//无对应用户
		if(storedUser == null) {
			return null;
		}

		PrivateKey privateKey = RsaUtil.getPrivateKey(privateKeyStr);
	    String decryptedPassword = RsaUtil.decryptRSA(user.getPassword(), privateKey);

		// 验证解密后的密码和数据库中存储的密码是否匹配
		if (!passwordEncoder.matches(decryptedPassword, storedUser.getPassword())) {
	        return null;
	    }

		String token = TokenUtil.sign(storedUser);
		storedUser.setToken(token);
		storedUser.setPassword("");
		return storedUser;

	}
	
	
	
	
	
	
	@Override
	public int getUserById(String userId) {

	    User user = userMapper.getUserById(userId);
	    String regex = "^1[3-9]\\d{9}$"; // 中国大陆手机号规范
	    
	    // 用户不存在
	    if (user == null) {
	        return 0; // 用户不存在
	    }
	    
	    // 手机号为空
	    if (userId == null || userId.isEmpty()) {
	        return -1; // 手机号为空
	    }
	    
	    // 手机号长度不为11位
	    if (userId.length() != 11) {
	        return -2; // 手机号长度错误
	    }
	    
	    // 首位数字不为1
	    if (!userId.startsWith("1")) {
	        return -3; // 手机号首位不为1
	    }
	    
	    // 手机号格式不匹配
	    if (!userId.matches(regex)) {
	        return -4; // 手机号格式不正确
	    }

	    // 手机号通过验证
	    return 1;

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
		if (storedUser == null) {
			return 0;
		}
		if(user.getUserName()==null || user.getUserName().equals("")) {
			user.setUserName(storedUser.getUserName());
		}
		if(user.getUserSex()==null || user.getUserSex()!=0 || user.getUserSex()!=1) {
			user.setUserSex(storedUser.getUserSex());
		}
		if(user.getUserImg()==null || user.getUserImg().equals("")) {
			user.setUserImg(storedUser.getUserImg());
		}
		
		return userMapper.updateUser(user);
	}

	@Override
	public int deleteUser(User user) {
		User storedUser = userMapper.getUserById(user.getUserId());
		// 无对应用户

		if (storedUser == null) {
			return 0;
		}
		// 密码不符
		if (!passwordEncoder.matches(user.getPassword(), storedUser.getPassword())) {

			return 0;
		}
		return userMapper.deleteUser(user);
	}

	@Override
	public int updateAuthorization(User user) {
		return userMapper.updateAuthorization(user);
	}






	@Override
	public int getBusinessIdById(String userId) {
		return userMapper.getBusinessIdById(userId);
	}
}
