package com.example.repository;

import com.example.model.Especialidad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadRepository extends MongoRepository<Especialidad, String> {
}
