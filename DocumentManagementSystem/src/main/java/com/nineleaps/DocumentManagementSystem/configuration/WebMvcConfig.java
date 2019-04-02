//package com.nineleaps.DocumentManagementSystem.configuration;
//
//import com.nineleaps.DocumentManagementSystem.DocumentManagementInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//    @Autowired
//    DocumentManagementInterceptor documentManagementInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(documentManagementInterceptor).addPathPatterns("/**");
//    }
//}
