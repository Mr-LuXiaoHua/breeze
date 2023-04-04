package com.breeze.admin.infrastructure.interceptor;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author 一枕清风
 * @date 2023/3/24
 */
@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {




    private static final String[] excludeStaticPathPatterns = {"/static/html/login.html", "/static/script/**",  "/static/ztree/**", "/static/zui/**", "/favicon.ico", "/static/html/index.html", "/static/html/dashboard.html"};
    private static final String[] excludeLoginPathPatterns = {"/login"};
    private static final String[] excludeAuthorizePathPatterns = {"/user/modify-password"};


    @Resource
    private LoginInterceptor loginInterceptor;
    @Resource
    private AuthorizeInterceptor authorizeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        String[] loginExcludes = ArrayUtils.addAll(excludeStaticPathPatterns, excludeLoginPathPatterns);
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(loginExcludes);

        String[] authorizeExcludes = ArrayUtils.addAll(loginExcludes, excludeAuthorizePathPatterns);
        registry.addInterceptor(authorizeInterceptor).addPathPatterns("/**").excludePathPatterns(authorizeExcludes);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
