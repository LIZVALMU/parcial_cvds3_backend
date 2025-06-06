package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Cita;
import com.example.repository.CitaRepository;

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

    public String generateIdUUID() {
        String idGenerateID = "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int randomNum = (int) (Math.random() * 10);
            sb.append(randomNum);
        }
        idGenerateID = sb.toString();
        return idGenerateID;
    }    public Cita crearCita(Cita cita) {
        cita.setId(generateIdUUID());
        
        if (cita.getFechaCita().isBefore(java.time.LocalDate.now())) {
            cita.setEstado("Rechazada");
        } else {
            cita.setEstado("Confirmada");
        }
        
        return citaRepository.save(cita);
    }

    public Cita cancelarCita(String id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));;
        cita.setEstado("Cancelada");
        return citaRepository.save(cita);
    }
}
