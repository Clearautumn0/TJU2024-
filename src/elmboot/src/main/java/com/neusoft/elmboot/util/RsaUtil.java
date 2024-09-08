package com.neusoft.elmboot.util;

import javax.crypto.Cipher;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

public class RsaUtil {
	public static PrivateKey getPrivateKey(String privateKeyStr) throws Exception {
	    byte[] keyBytes = Base64.getDecoder().decode(privateKeyStr);
	    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
	    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	    return keyFactory.generatePrivate(spec);
	}
	
	public static String decryptRSA(String encryptedData, PrivateKey privateKey) throws Exception {
	    Cipher cipher = Cipher.getInstance("RSA");
	    cipher.init(Cipher.DECRYPT_MODE, privateKey);
	    byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
	    byte[] decryptedBytes = cipher.doFinal(decodedBytes);
	    return new String(decryptedBytes, "UTF-8");
	}
	
	
	
	
    // 生成公钥，使用私钥的 modulus 以及常用的 publicExponent 65537
    public static PublicKey getPublicKeyFromPrivateKey(PrivateKey privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        // 获取私钥的规格（modulus 和 privateExponent）
        RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);

        // 使用常见的公钥指数 65537
        BigInteger publicExponent = BigInteger.valueOf(65537);

        // 使用私钥的 modulus 和公钥的 exponent 生成公钥
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(privateKeySpec.getModulus(), publicExponent);
        
        // 生成公钥
        return keyFactory.generatePublic(publicKeySpec);
    }

    // 公钥转成 Base64 编码的字符串
    public static String encodePublicKeyToBase64(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    // 解码 Base64 字符串私钥
    public static PrivateKey getPrivateKeyFromBase64(String base64PrivateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(base64PrivateKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(new BigInteger(1, keyBytes), BigInteger.valueOf(65537));
        return keyFactory.generatePrivate(privateKeySpec);
    }
}
