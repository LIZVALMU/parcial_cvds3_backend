package com.example.controller;

import com.example.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCitas() {
        System.out.println("Fetching all citas");
        return ResponseEntity.ok("Hola");

    }


}
