package com.example.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.model.Especialidad;
import com.example.repository.EspecialidadRepository;

class EspecialidadServiceTest {

    @Mock
    private EspecialidadRepository especialidadRepository;

    @InjectMocks
    private EspecialidadService especialidadService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        // Arrange
        Especialidad medicinaGeneral = new Especialidad();
        medicinaGeneral.setId("1");
        medicinaGeneral.setNombre("Medicina General");
        
        Especialidad psicologia = new Especialidad();
        psicologia.setId("2");
        psicologia.setNombre("Psicología");
        
        List<Especialidad> especialidadesEsperadas = Arrays.asList(medicinaGeneral, psicologia);
        
        when(especialidadRepository.findAll()).thenReturn(especialidadesEsperadas);

        // Act
        List<Especialidad> especialidadesObtenidas = especialidadService.findAll();

        // Assert
        assertEquals(2, especialidadesObtenidas.size());
        assertEquals("Medicina General", especialidadesObtenidas.get(0).getNombre());
        assertEquals("Psicología", especialidadesObtenidas.get(1).getNombre());
    }

    @Test
    void findById() {
        // Arrange
        String id = "1";
        Especialidad medicinaGeneral = new Especialidad();
        medicinaGeneral.setId(id);
        medicinaGeneral.setNombre("Medicina General");
        medicinaGeneral.setDescripcion("Descripción de medicina general");
        medicinaGeneral.setDoctor("Dr. Juan Pérez");
        medicinaGeneral.setUbicacion("Consultorio 101");
        
        when(especialidadRepository.findById(id)).thenReturn(Optional.of(medicinaGeneral));

        // Act
        Especialidad especialidadObtenida = especialidadService.findById(id);

        // Assert
        assertEquals(id, especialidadObtenida.getId());
        assertEquals("Medicina General", especialidadObtenida.getNombre());
        assertEquals("Dr. Juan Pérez", especialidadObtenida.getDoctor());
    }

    @Test
    void findById_NoExiste() {
        // Arrange
        String id = "999";
        when(especialidadRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> especialidadService.findById(id));
    }

    @Test
    void save() {
        // Arrange
        Especialidad especialidadAGuardar = new Especialidad();
        especialidadAGuardar.setNombre("Odontología");
        especialidadAGuardar.setDescripcion("Descripción de odontología");
        
        when(especialidadRepository.save(especialidadAGuardar)).thenReturn(especialidadAGuardar);

        // Act
        Especialidad especialidadGuardada = especialidadService.save(especialidadAGuardar);

        // Assert
        assertEquals("Odontología", especialidadGuardada.getNombre());
        assertEquals("Descripción de odontología", especialidadGuardada.getDescripcion());
    }
}
