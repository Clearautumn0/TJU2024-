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

//	@Override
//	public User getUserByIdByPass(String userId, String password) {
//		User user = userMapper.getUserById(userId);
//		return userMapper.getUserByIdByPass(user);
//	}



//	@Override
//	public User getUserByIdByPass(User user) {
//	    User reuser = userMapper.getUserByIdByPass(user);
//	    if (reuser != null) {
//	        reuser.setPassword("");//返回时不返回用户的密码
//	    }
//	    return reuser; // 这里返回null也是安全的，因为已经做了非空检查
//	}


//	@Override
//	public User getUserByIdByPass(User user) {
//		User storedUser = userMapper.getUserById(user.getUserId());
//		// 无对应用户
//		if (storedUser == null) {
//			return null;
//		}
//		// 密码不符
//		if (!passwordEncoder.matches(user.getPassword(), storedUser.getPassword())) {
//			return null;
//		}
//		storedUser.setPassword("");
//		return storedUser;
//	}

	
	
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
		String regex = "^1[3-9]\\d{9}$";//中国大陆手机号规范
		User user = userMapper.getUserById(userId);
		if (user != null && userId.matches(regex)) {
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

		if (storedUser == null) {
			return 0;
		}
		// 密码不符
		if (!passwordEncoder.matches(user.getPassword(), storedUser.getPassword())) {

			return 0;
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

}
