package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.EspecialidadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/especialidades")
@Tag(name = "Especialidades", description = "Operaciones relacionadas con las especialidades médicas")
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;    @Operation(summary = "Obtener todas las especialidades", description = "Obtiene el listado de todas las especialidades médicas disponibles")
    @GetMapping("/all")
    public ResponseEntity<?> getAllEspecialidades() {
        return ResponseEntity.ok(especialidadService.findAll());
    }    @Operation(summary = "Obtener especialidad por ID", description = "Obtiene los detalles de una especialidad médica por su identificador")
    @GetMapping("/{id}")
    public ResponseEntity<?> getEspecialidadById(@PathVariable String id) {
        return ResponseEntity.ok(especialidadService.findById(id));
    }
}
