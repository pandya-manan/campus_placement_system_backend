package com.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@OpenAPIDefinition
@SecurityScheme(
		name="Bearer Authentication",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer",
		in = SecuritySchemeIn.HEADER
		
)
public class SwaggerConfig {

    @Bean
    public OpenAPI campusPlacementAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Campus Placement System - Login API")
                        .description("Login API built for the Campus Placement System using Spring Boot")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Manan Pandya")
                                .email("pandyamanan100@gmail.com"))
                        .license(new License()
                                .name("MIT License")));
    }

}