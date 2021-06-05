package com.example.wheat.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(new UserLoginInterceptor())
////                .addPathPatterns("/**")  //拦截所有接口
////                .excludePathPatterns("/register","/login","/swagger-ui.html"); //不拦截的接口
//    }
}
