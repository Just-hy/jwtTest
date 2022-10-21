package com.example.jwttest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
//@EnableMethodSecurity(securedEnabled = true,prePostEnabled = true)
@MapperScan(basePackages = "com.example.jwttest.mapper")
public class JwtTestApplication {

/*    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }*/

    public static void main(String[] args) {
        SpringApplication.run(JwtTestApplication.class, args);
    }

}
