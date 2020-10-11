package com.birds.configs;

import com.birds.interceptor.ClickLogInterceptor;
import com.birds.interceptor.CurrentLimitInterceptor;
import com.birds.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private TokenInterceptor tokenInterceptor ;
    @Resource
    private ClickLogInterceptor clickLogInterceptor;
    @Resource
    private CurrentLimitInterceptor currentLimitInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(clickLogInterceptor).addPathPatterns("/**");
        registry.addInterceptor(currentLimitInterceptor).addPathPatterns("/**");
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/api/v1/user/login").excludePathPatterns("/api/v1/user/register");
    }
}