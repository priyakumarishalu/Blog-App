package com.priya.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class swaggerConfig {

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Spring Boot Blog Rest API",
                "Spring Boot Blog Rest Api Documentation ",
                "1",
                "Terms of services",
                new Contact("Priya Kumari","www.blogApp.com","priyakumarishalu@gmail.com"),
                "License of api",
                "Api URL",
                Collections.emptyList()
        );
    }

    @Bean
   public Docket apiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.priya.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
