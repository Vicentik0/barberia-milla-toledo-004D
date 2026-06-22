package com.promociones.promocion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = { 
    org.springdoc.core.configuration.SpringDocHateoasConfiguration.class 
})
public class PromocionApplication {

    public static void main(String[] args) {
        SpringApplication.run(PromocionApplication.class, args);
    }
}