package com.example.service;

import com.example.model.Cita;
import com.example.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> findAll() {
        return citaRepository.findAll();
    }

    public List<Cita> obtenerCitasPorCorreo(String correo) {
        return citaRepository.findByCorreo(correo);
    }

    public List<Cita> filtrarCitasPorEstado(String correo, String estado) {
        return citaRepository.findByCorreoAndEstado(correo, estado);
    }

    public Cita crearCita(Cita cita) {
        return citaRepository.save(cita);
    }

    public Cita cancelarCita(String id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));;
        cita.setEstado("Cancelada");
        return citaRepository.save(cita);
    }
}
