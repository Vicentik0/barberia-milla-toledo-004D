package com.pago.pago.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pago.pago.model.Pagos;
import com.pago.pago.repository.PagosRepository;

@Service
public class PagosService {
    @Autowired
    private PagosRepository pagoRepository;

    public List<Pagos> listar() {
        return pagoRepository.findAll();
    }

    public Pagos guardarPago(Pagos pago) {
        return pagoRepository.save(pago);
    }

    public Pagos buscarPorId(Long id) {
        return pagoRepository.findById(id).orElse(null);
    }

    public Pagos actualizarPago(Long id, Pagos pago) {
        Pagos pagoExistente = pagoRepository.findById(id).orElse(null);

        if (pagoExistente != null) {
            pagoExistente.setIdCliente(pago.getIdCliente());
            pagoExistente.setIdCita(pago.getIdCita());
            pagoExistente.setMonto(pago.getMonto());
            pagoExistente.setMetodoPago(pago.getMetodoPago());
            pagoExistente.setEstadoPago(pago.getEstadoPago());

            return pagoRepository.save(pagoExistente);
        }

        return null;
    }

    public void eliminar(Long id) {
        pagoRepository.deleteById(id);
    }

}
