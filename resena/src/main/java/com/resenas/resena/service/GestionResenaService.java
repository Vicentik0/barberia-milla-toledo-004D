package com.resenas.resena.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.resenas.resena.model.Resena;
import com.resenas.resena.repository.ResenaRepository;

@Service
public class GestionResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Resena obtenerResenaCompleta(Long id) {
        Optional<Resena> resenaOpt = resenaRepository.findById(id);
        
        if (resenaOpt.isPresent()) {
            Resena resena = resenaOpt.get();
            try {
                Object barbero = webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8099/api/v1/barberos/" + resena.getBarberoId())
                        .retrieve()
                        .bodyToMono(Object.class)
                        .block(); 
                
                resena.setDatosBarbero(barbero);
            } catch (Exception e) {
                resena.setDatosBarbero("No se pudieron cargar los datos del barbero: " + e.getMessage());
            }
            return resena;
        }
        return null;
    }

    public Resena guardarResena(Resena resena) {
        return resenaRepository.save(resena);
    }
}
