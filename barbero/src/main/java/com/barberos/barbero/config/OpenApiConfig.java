package com.barberos.barbero.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;


@Configuration
public class OpenApiConfig {
     @Bean
    public OpenAPI barberosOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Barberos - Sistema de Gestión Barbería")
                        .version("1.0.0")
                        .description("Documentación del microservicio de barberos de la barbería."))
                        .servers(List.of(
                                new Server().url("http://localhost:8099").description("API Gateway")
                ));
    }

}
