package com.citas.cita.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.citas.cita.Repository.CitaRepository;
import com.citas.cita.model.Cita;

@Service
public class CitaService {
    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<Cita> listar(){
        return citaRepository.findAll();
    }

    public Cita guardarCita(Cita cita) {
        if (cita.getIdCliente() != null) {
        Object datosCliente = webClientBuilder.build()
                .get()
                .uri("http://localhost:9082/cliente/" + cita.getIdCliente())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    if (cita.getIdBarbero() != null) {
        Object datosBarbero = webClientBuilder.build()
                .get()
                .uri("http://localhost:8083/barberos/" + cita.getIdBarbero())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    return citaRepository.save(cita);
    }

    public Cita buscarPorId(Long id) {
        Optional<Cita> cita = citaRepository.findById(id);
        return cita.orElse(null);
    }

    public Cita actualizarCita(Long id, Cita cita) {
        Optional<Cita> citaExistente = citaRepository.findById(id);

        if (citaExistente.isPresent()) {
            Cita c = citaExistente.get();

            c.setIdCliente(cita.getIdCliente());
            c.setIdBarbero(cita.getIdBarbero());
            c.setIdSucursal(cita.getIdSucursal());
            c.setIdProducto(cita.getIdProducto());
            c.setServicio(cita.getServicio());
            c.setFecha(cita.getFecha());
            c.setEstado(cita.getEstado());

            return citaRepository.save(c);
        }

        return null;
    }

    public void eliminar(Long id) {
        citaRepository.deleteById(id);
    }

}
