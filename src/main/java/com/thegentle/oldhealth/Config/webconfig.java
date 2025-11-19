package com.thegentle.oldhealth.Config;

import com.thegentle.oldhealth.Interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *配置类
 */

@Configuration
public class webconfig implements WebMvcConfigurer {
    @Autowired
    private TokenInterceptor tokenInterceptor;
    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        //注册添加拦截器
        registry.addInterceptor(tokenInterceptor)
                        .addPathPatterns("/**")       //拦截所有请求
                    .excludePathPatterns("/login")  //登录接口不拦截
                .excludePathPatterns("/register");  //注册接口不拦截
    }
}
