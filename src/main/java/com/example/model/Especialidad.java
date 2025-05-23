package com.example.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "especialidades")
public class Especialidad {
    @Id
    private String id;
    private String nombre;
    private String descripcion;
    private String imagenUrl;
    private String doctor;
    private String ubicacion;
}
