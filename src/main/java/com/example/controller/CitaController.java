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

    @GetMapping("/correo")
    public ResponseEntity<?> getCitasByCorreo(@RequestParam String correo) {
        return ResponseEntity.ok(citaService.obtenerCitasPorCorreo(correo));
    }

    @GetMapping("/estado")
    public ResponseEntity<?> filtrarCitasPorEstado(@RequestParam String correo,@RequestParam String estado) {
        return ResponseEntity.ok(citaService.filtrarCitasPorEstado(correo, estado));
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearCita(@RequestBody Cita cita) {
        return ResponseEntity.ok(citaService.crearCita(cita));
    }

    @PostMapping("/cancelar")
    public ResponseEntity<?> cancelarCita(@RequestParam String id) {
        return ResponseEntity.ok(citaService.cancelarCita(id));
    }




}
