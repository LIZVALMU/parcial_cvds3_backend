package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Cita;
import com.example.service.CitaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/citas")
@Tag(name = "Citas", description = "Operaciones relacionadas con la gestión de citas médicas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Operation(summary = "Obtener todas las citas", description = "Obtiene el listado de todas las citas médicas")
    @GetMapping("/all")
    public ResponseEntity<?> getAllCitas() {
        return ResponseEntity.ok(citaService.findAll());
    }

    @Operation(summary = "Obtener citas por correo", description = "Obtiene el historial de citas médicas de un usuario identificado por su correo")
    @GetMapping("/{correo}/correo")
    public ResponseEntity<?> getCitasByCorreo(@PathVariable String correo) {
        return ResponseEntity.ok(citaService.obtenerCitasPorCorreo(correo));
    }

    @Operation(summary = "Filtrar citas por estado", description = "Filtra las citas de un usuario por estado (Confirmada/Cancelada)")
    @GetMapping("/{correo}/{estado}/filtrar")
    public ResponseEntity<?> filtrarCitasPorEstado(@PathVariable String correo, @PathVariable String estado) {
        return ResponseEntity.ok(citaService.filtrarCitasPorEstado(correo, estado));
    }

    @Operation(summary = "Programar una cita", description = "Crea una nueva cita médica y valida que la fecha sea válida")
    @PostMapping("/crear")
    public ResponseEntity<?> crearCita(@RequestBody Cita cita) {
        return ResponseEntity.ok(citaService.crearCita(cita));
    }

    @Operation(summary = "Cancelar una cita", description = "Cambia el estado de una cita a 'Cancelada'")
    @PostMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarCita(@PathVariable String id) {
        return ResponseEntity.ok(citaService.cancelarCita(id));
    }

}
