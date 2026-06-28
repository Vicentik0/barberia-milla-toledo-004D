package com.cliente.clientes.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server; 
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI clientesOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("API Clientes - Sistema de Gestion Barberia")
                        .version("1.0")
                        .description("Documentación del Sistema Barberia"))
                .servers(List.of(
                        new Server().url("http://localhost:8099").description("API Gateway")
                ));
    }

}
