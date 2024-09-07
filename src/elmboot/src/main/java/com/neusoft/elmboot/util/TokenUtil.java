package com.neusoft.elmboot.util;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.neusoft.elmboot.po.User;

public class TokenUtil {
	private static final long EXPIRE_TIME = 10*60*60*1000;	//10h过期
	private static final String TOKEN_SECRET="aComplexSalt()";  //密钥盐
	/**
     * 签名生成
     * @param user
     * @return
     */
    public static String sign(User user){
        String token = null;
        try {
            token = JWT.create()
                    .withIssuer("elbServer")
                    .withClaim("userId", user.getUserId())
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }
 
    /**
     * 签名验证
     * @param token
     * @return
     */
    public static boolean verify(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("elbServer").build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("userId: " + jwt.getClaim("userId").asString());
            System.out.println("过期时间：      " + jwt.getExpiresAt());
            return true;
        } catch (Exception e){
        	System.out.println("验证失败： " + e.getMessage()); // 打印异常信息
            return false;
        }
    }

}
