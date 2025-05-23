package com.example.controller;

import com.example.model.Cita;
import com.example.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/citas")
public class CitaController {
    @Autowired
    private CitaService citaService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCitas() {
        return ResponseEntity.ok(citaService.findAll());
    }

    @GetMapping("/{correo}/correo")
    public ResponseEntity<?> getCitasByCorreo(@PathVariable  String correo) {
        return ResponseEntity.ok(citaService.obtenerCitasPorCorreo(correo));
    }

    @GetMapping("/{correo}/{estado}/filtar")
    public ResponseEntity<?> filtrarCitasPorEstado(@PathVariable  String correo,@PathVariable  String estado) {
        return ResponseEntity.ok(citaService.filtrarCitasPorEstado(correo, estado));
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearCita(@PathVariable  Cita cita) {
        return ResponseEntity.ok(citaService.crearCita(cita));
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarCita(@PathVariable  String id) {
        return ResponseEntity.ok(citaService.cancelarCita(id));
    }




}
