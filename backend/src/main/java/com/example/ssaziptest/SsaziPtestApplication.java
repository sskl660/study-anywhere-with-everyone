package com.example.ssaziptest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.MultipartConfigElement;

//Entity에서 EntityListeners(AuditingEntityListener.class) 하고 이 EnableJpaAuditing 추가 안하면 CreatedDate 안먹음
@EnableJpaAuditing
@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
@EnableScheduling
public class SsaziPtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsaziPtestApplication.class, args);
    }
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setMaxFileSize("512MB");
//        factory.setMaxRequestSize("512MB");
        return factory.createMultipartConfig();
    }

    @Bean
    public MultipartResolver multipartResolver() {
        org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(512000000);
        return multipartResolver;
    }
    @Bean
    public CommonsMultipartResolver multipartResolver2()
    {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }
    @Bean
    public MultipartFilter multipartFilter(){

        MultipartFilter multipartFilter = new MultipartFilter();
        multipartFilter.setMultipartResolverBeanName("multipartResolver2");
        return multipartFilter;
    }

}
