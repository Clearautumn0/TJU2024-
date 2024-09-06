package com.neusoft.elmboot.interceptor;
 
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neusoft.elmboot.util.TokenUtil;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@Component
public class TokenInterceptor implements HandlerInterceptor {
	
	private final ObjectMapper objectMapper = new ObjectMapper();
 
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,@NonNull Object handler)throws Exception{
        String httpMethod = request.getMethod();
        String requestURI = request.getRequestURI();
    	//	跨域访问前提请求
    	if(httpMethod.equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
    	//	查询商家信息
    	if(requestURI.startsWith("businesses") && httpMethod.equals("GET")) {
    		response.setStatus(HttpServletResponse.SC_OK);
            return true;
    	}
    	//	查询食品信息
    	if(requestURI.startsWith("foods") && httpMethod.equals("GET")) {
    		response.setStatus(HttpServletResponse.SC_OK);
            return true;
    	}
    	//	登录 注册
    	if(requestURI.startsWith("foods") && httpMethod.equals("POST")) {
    		response.setStatus(HttpServletResponse.SC_OK);
            return true;
    	}
    	
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        if(token != null && TokenUtil.verify(token)){
        	System.out.println("通过拦截器");
            return true;
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("msg", "token verify fail");
        jsonMap.put("code","50000");
        
        // 发送JSON响应
        try{
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().print(objectMapper.writeValueAsString(jsonMap));
            System.out.println("认证失败，未通过拦截器");
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(500);
            return false;
        }
        return false;
    }
}