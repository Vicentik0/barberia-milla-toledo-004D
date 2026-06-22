package com.promociones.promocion.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server; 
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Barberia - Promociones")
                        .version("1.0")
                        .description("Microservicio de gestión de promociones y ofertas"))
                .servers(List.of(
                        new Server().url("http://localhost:8099").description("API Gateway") 
                ));
    }
}
