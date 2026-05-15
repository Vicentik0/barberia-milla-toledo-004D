package com.barberos.barbero.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barberos.barbero.model.Barbero;
import com.barberos.barbero.repository.BarberoRepository;

@Service
public class BarberoService 
{
    @Autowired
    private BarberoRepository barberoRepository;

    public List<Barbero> listar() 
    {
        return barberoRepository.findAll();
    }

    public Barbero guardarBarbero(Barbero barbero) 
    {
        return barberoRepository.save(barbero);
    }

    public Barbero buscarPorId(Long id) 
    {
        Optional<Barbero> barbero = barberoRepository.findById(id);

        if (barbero.isPresent()) 
        {
            return barbero.get();
        }

        return null;
    }

    public Barbero actualizarBarbero(Long id, Barbero barbero) 
    {
        Optional<Barbero> barberoExistente = barberoRepository.findById(id);

        if (barberoExistente.isPresent()) 
        {
            Barbero b = barberoExistente.get();

            b.setNombre(barbero.getNombre());
            b.setEspecialidad(barbero.getEspecialidad());
            b.setTelefono(barbero.getTelefono());

            return barberoRepository.save(b);
        }

        return null;
    }

   public void eliminar(Long id)
   {
        barberoRepository.deleteById(id);
   }
}
