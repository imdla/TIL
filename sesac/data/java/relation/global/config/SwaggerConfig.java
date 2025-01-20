package com.example.relation.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("프로젝트명 API Document")
                .version("v1.0.0")
                .description("API 명세서");

        return new OpenAPI()
                .info(info);
    }
}
