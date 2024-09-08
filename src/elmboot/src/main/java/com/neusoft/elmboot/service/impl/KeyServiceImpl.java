package com.neusoft.elmboot.service.impl;

import java.security.PrivateKey;
import java.security.PublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.neusoft.elmboot.service.KeyService;
import com.neusoft.elmboot.util.RsaUtil;




@Service
public class KeyServiceImpl implements KeyService{
	 @Value("${security.rsa.private-key}")
	    private String privateKeyStr;
	 @Override
	 public String getPublicKey() throws Exception {
	        // 从 Base64 编码的私钥字符串中获取私钥
	        PrivateKey privateKey = RsaUtil.getPrivateKey(privateKeyStr);

	        // 从私钥生成公钥
	        PublicKey publicKey = RsaUtil.getPublicKeyFromPrivateKey(privateKey);

	        // 将公钥编码为 Base64 字符串并返回给前端
	        return RsaUtil.encodePublicKeyToBase64(publicKey);
	    }
	
	
}
