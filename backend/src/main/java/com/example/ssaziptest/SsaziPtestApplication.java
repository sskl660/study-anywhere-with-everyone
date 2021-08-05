package com.example.ssaziptest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

//Entity에서 EntityListeners(AuditingEntityListener.class) 하고 이 EnableJpaAuditing 추가 안하면 CreatedDate 안먹음
@EnableJpaAuditing
@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
@EnableScheduling
public class SsaziPtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsaziPtestApplication.class, args);
    }

}
