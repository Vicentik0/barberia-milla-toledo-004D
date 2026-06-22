package com.servicios.servicios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicios.servicios.model.Servicio;
import com.servicios.servicios.repository.ServicioRepository;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public List<Servicio> listar() {
        return servicioRepository.findAll();
    }

    public Servicio guardarServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public Servicio buscarPorId(Long id) {
        return servicioRepository.findById(id).orElse(null);
    }

    public Servicio actualizarServicio(Long id, Servicio servicio) {
        Servicio servicioExistente = servicioRepository.findById(id).orElse(null);

        if (servicioExistente != null) {
            servicioExistente.setNombre(servicio.getNombre());
            servicioExistente.setDescripcion(servicio.getDescripcion());
            servicioExistente.setPrecio(servicio.getPrecio());
            servicioExistente.setDuracionMinutos(servicio.getDuracionMinutos());
            return servicioRepository.save(servicioExistente);
        }

        return null;
    }

    public void eliminar(Long id) {
        servicioRepository.deleteById(id);
    }

}
