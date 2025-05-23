package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Especialidad;
import com.example.repository.EspecialidadRepository;

@Service
public class EspecialidadService {
    @Autowired
    private EspecialidadRepository especialidadRepository;
    
    public List<Especialidad> findAll() {
        return especialidadRepository.findAll();
    }
    
    public Especialidad findById(String id) {
        return especialidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
    }
    
    public Especialidad save(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }
}
