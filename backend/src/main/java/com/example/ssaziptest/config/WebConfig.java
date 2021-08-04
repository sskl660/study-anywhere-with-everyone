package com.example.ssaziptest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://13.125.119.76:3000", "http://localhost:3000")//앞은 서버 배포중인 프론트 포트, 뒤는 로컬 포트
                //.allowedOrigins("http://localhost:3000")
                .allowCredentials(true);
    }
}