package com.neusoft.elmboot.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.neusoft.elmboot.interceptor.TokenInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	private TokenInterceptor tokenInterceptor;
	
	public WebMvcConfig(TokenInterceptor tokenInterceptor) {
		this.tokenInterceptor = tokenInterceptor;
	}
	
	@Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        /*
         * addMapping：配置可以被跨域的路径，可以任意配置，可以具体到直接请求路径。
         * allowedMethods：允许的请求方式，如：POST、GET、PUT、DELETE等。
         * allowedOrigins：允许访问的url，可以固定单条或者多条内容，如："http://www.baidu.com"。
         * allowedHeaders：允许的请求header，可以自定义设置任意请求头信息。
         * maxAge：配置预检请求的有效时间
         */
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8081")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
                .allowedHeaders("*")
                .maxAge(36000);
    }
	
//	@Override
//    public void configureAsyncSupport(@NonNull AsyncSupportConfigurer configurer){
//        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
//        configurer.setDefaultTimeout(30000);
//    }
 
    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry){
        List<String> excludePath = new ArrayList<>();
        //排除拦截，除了注册登录(此时还没token)，其他都拦截
        excludePath.add("/users/login");  //登录
        excludePath.add("/public-key");  //登录
        excludePath.add("/captcha");  //验证码
//        excludePath.add("/orders/**");  
        excludePath.add("/static/**");  //静态资源
        excludePath.add("/assets/**");  //静态资源
        
 
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);
    }

}