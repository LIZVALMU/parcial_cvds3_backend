package com.example.repository;

import com.example.model.Cita;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends MongoRepository<Cita, String> {

    List<Cita> findByCorreo(String correo);

    List<Cita> findByCorreoAndEstado(String correo, String estado);
}
