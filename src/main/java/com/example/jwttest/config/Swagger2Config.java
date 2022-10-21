package com.example.jwttest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author:  Huangyi
 * @Date:  2022/10/21
 * @Description:
 * @Version:  1.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket docket(){
        List infos=new ArrayList<>();
        infos.add("java2205 全体学生共做项目");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfo(
                                //标题
                                "Java2205_third_project",
                                //描述
                                "Java2205三阶段项目",
                                //版本
                                "1.0",
                                //服务地址
                                "http://www.java2205.com",
                                //作者信息
                                new Contact("java2205", "http://www.java2205.com", "java2205@163.com"),
                                //许可证
                                "java2205_ok",
                                //许可证URL
                                "http://www.java2205.com",
                                //扩展信息
                                infos
                        )
                ).groupName("java2205")
                .select()
                //不显示错误的接口地址
                //.paths(Predicates.not(PathSelectors.regex("/error.*")))//错误路径不监控
                //扫描所有的包 可以扫描指定的包
                .apis(RequestHandlerSelectors.basePackage("com.example.jwttest.controller"))
                .build();
    }
}