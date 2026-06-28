package com.promociones.promocion.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promociones.promocion.model.Promocion;
import com.promociones.promocion.repository.PromocionRepository;

@Service
public class PromocionService {

    @Autowired
    private PromocionRepository promocionRepository;

    public List<Promocion> listar() {
        return promocionRepository.findAll();
    }

    public Promocion guardarPromocion(Promocion promocion) {
        return promocionRepository.save(promocion);
    }

    public Optional<Promocion> buscarPorId(Long id) {
        return promocionRepository.findById(id);
    }

    public Promocion actualizarPromocion(Long id, Promocion promocion) {
        Optional<Promocion> existente = promocionRepository.findById(id);
        if (existente.isPresent()) {
            Promocion p = existente.get();
            p.setNombre(promocion.getNombre());
            p.setDescripcion(promocion.getDescripcion());
            p.setDescuento(promocion.getDescuento());
            p.setActiva(promocion.getActiva());
            return promocionRepository.save(p);
        }
        return null;
    }

    public void eliminar(Long id) {
        promocionRepository.deleteById(id);
    }
}