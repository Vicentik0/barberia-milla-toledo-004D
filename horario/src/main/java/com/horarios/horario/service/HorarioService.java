package com.horarios.horario.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.horarios.horario.model.Horario;
import com.horarios.horario.repository.HorarioRepository;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public List<Horario> listar() {
        return horarioRepository.findAll();
    }

    public Horario guardarHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    public Horario buscarPorId(Long id) {
        return horarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado con ID: " + id));
    }

    public Horario actualizarHorario(Long id, Horario horario) {
        Horario h = horarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede actualizar. Horario no encontrado con ID: " + id));

        h.setDia(horario.getDia());
        h.setHoraInicio(horario.getHoraInicio());
        h.setHoraFin(horario.getHoraFin());
        h.setDisponible(horario.getDisponible());

        return horarioRepository.save(h);
    }

    public void eliminar(Long id) {
        if (!horarioRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Horario no encontrado con ID: " + id);
        }
        horarioRepository.deleteById(id);
    }
}