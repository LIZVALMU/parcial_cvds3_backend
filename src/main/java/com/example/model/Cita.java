package com.example.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "citas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cita {
    @Id
    private String id;
    private String nombreCompleto;
    private String cedula;
    private String correo;
    private LocalDate fechaCita;
    private String especialidad;
    private String doctor;
    private String ubicacion;
    private String estado;
}
