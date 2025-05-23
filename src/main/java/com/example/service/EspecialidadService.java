package com.example.service;

import com.example.repository.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EspecialidadService {
    @Autowired
    private EspecialidadRepository especialidadRepository;
}
