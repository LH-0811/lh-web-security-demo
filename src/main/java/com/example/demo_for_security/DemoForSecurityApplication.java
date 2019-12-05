package com.example.demo_for_security;

import com.lhit.starter.security.properties.LhitSecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableConfigurationProperties(LhitSecurityProperties.class)
@MapperScan("com.example.demo_for_security.dao")
public class DemoForSecurityApplication {


    public static void main(String[] args) {
        SpringApplication.run(DemoForSecurityApplication.class, args);
    }


}
