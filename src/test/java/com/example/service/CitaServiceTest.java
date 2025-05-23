package com.example.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.model.Cita;
import com.example.repository.CitaRepository;

class CitaServiceTest {

    @Mock
    private CitaRepository citaRepository;

    @InjectMocks
    private CitaService citaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearCitaFechaFutura() {
        Cita cita = new Cita();
        cita.setNombreCompleto("Juan Pérez");
        cita.setCedula("123456789");
        cita.setCorreo("juan@example.com");
        cita.setFechaCita(LocalDate.now().plusDays(1));
        cita.setEspecialidad("Medicina General");
        cita.setDoctor("Dr. Ejemplo");
        cita.setUbicacion("Consultorio 101");
        
        when(citaRepository.save(any(Cita.class))).thenAnswer(invocation -> {
            Cita citaArgumento = invocation.getArgument(0);
            citaArgumento.setId("1234567890");
            return citaArgumento;
        });

        Cita citaCreada = citaService.crearCita(cita);

        assertNotNull(citaCreada.getId());
        assertEquals("Confirmada", citaCreada.getEstado());
    }

    @Test
    void crearCitaFechaPasada() {
        Cita cita = new Cita();
        cita.setNombreCompleto("Juan Pérez");
        cita.setCedula("123456789");
        cita.setCorreo("juan@example.com");
        cita.setFechaCita(LocalDate.now().minusDays(1));
        cita.setEspecialidad("Medicina General");
        cita.setDoctor("Dr. Ejemplo");
        cita.setUbicacion("Consultorio 101");
        
        when(citaRepository.save(any(Cita.class))).thenAnswer(invocation -> {
            Cita citaArgumento = invocation.getArgument(0);
            citaArgumento.setId("1234567890");
            return citaArgumento;
        });

        Cita citaCreada = citaService.crearCita(cita);

        assertNotNull(citaCreada.getId());
        assertEquals("Rechazada", citaCreada.getEstado());
    }

    @Test
    void cancelarCita() {
        String idCita = "1234567890";
        Cita citaExistente = new Cita();
        citaExistente.setId(idCita);
        citaExistente.setEstado("Confirmada");
        
        when(citaRepository.findById(idCita)).thenReturn(Optional.of(citaExistente));
        when(citaRepository.save(any(Cita.class))).thenReturn(citaExistente);

        Cita citaCancelada = citaService.cancelarCita(idCita);

        assertEquals("Cancelada", citaCancelada.getEstado());
    }

    @Test
    void obtenerCitasPorCorreo() {
        String correo = "juan@example.com";
        List<Cita> citasEsperadas = Arrays.asList(
                new Cita("1", "Juan Pérez", "123456789", correo, LocalDate.now().plusDays(1), "Medicina General", "Dr. Ejemplo", "Consultorio 101", "Confirmada"),
                new Cita("2", "Juan Pérez", "123456789", correo, LocalDate.now().plusDays(2), "Odontología", "Dra. Ejemplo", "Consultorio 202", "Confirmada")
        );
        
        when(citaRepository.findByCorreo(correo)).thenReturn(citasEsperadas);

        List<Cita> citasObtenidas = citaService.obtenerCitasPorCorreo(correo);

        assertEquals(2, citasObtenidas.size());
        assertEquals("Medicina General", citasObtenidas.get(0).getEspecialidad());
        assertEquals("Odontología", citasObtenidas.get(1).getEspecialidad());
    }

    @Test
    void filtrarCitasPorEstado() {
        String correo = "juan@example.com";
        String estado = "Confirmada";
        List<Cita> citasEsperadas = Arrays.asList(
                new Cita("1", "Juan Pérez", "123456789", correo, LocalDate.now().plusDays(1), "Medicina General", "Dr. Ejemplo", "Consultorio 101", estado)
        );
        
        when(citaRepository.findByCorreoAndEstado(correo, estado)).thenReturn(citasEsperadas);

        List<Cita> citasObtenidas = citaService.filtrarCitasPorEstado(correo, estado);

        assertEquals(1, citasObtenidas.size());
        assertEquals(estado, citasObtenidas.get(0).getEstado());
    }
}
