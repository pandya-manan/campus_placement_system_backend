package com.signup.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI campusPlacementAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Campus Placement System - Signup API")
                        .description("Signup API built for the Campus Placement System using Spring Boot")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Manan Pandya")
                                .email("pandyamanan100@gmail.com"))
                        .license(new License()
                                .name("MIT License")));
    }

}