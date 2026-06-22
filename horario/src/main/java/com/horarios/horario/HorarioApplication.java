package com.horarios.horario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = { 
    org.springdoc.core.configuration.SpringDocHateoasConfiguration.class 
})
public class HorarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(HorarioApplication.class, args);
    }
}
