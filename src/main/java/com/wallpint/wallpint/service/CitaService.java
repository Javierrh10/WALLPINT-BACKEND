package com.wallpint.wallpint.service;

import com.wallpint.wallpint.model.Cita;
import com.wallpint.wallpint.repository.CitaRepository;
import com.wallpint.wallpint.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Esta clase ...
 *
 * @Autor: Javier Raposo Huelva
 * @Version: 2026:04
 */
@Service
public class CitaService {

    private final CitaRepository citaRepository;

    // =============== Constructor para inyectar el servicio ===============
    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    // =============== Métodos para el manejo de la lógica relacionada con las citas ===============

    // Listar todas las citas (Para el calendario del administrador)
    public List<Cita> obtenerTodas() {
        return citaRepository.findAll();
    }

    // Buscar citas de un cliente en concreto
    public List<Cita> obtenerPorCliente(Long clienteId) {
        return citaRepository.findByClienteId(clienteId);
    }

    // Guardar una nueva cita
    public Cita guardarCita(Cita cita) {
        // Estado por defecto al crear una cita nueva
        if (cita.getEstado() == null || cita.getEstado().isEmpty()) {
            cita.setEstado("PENDIENTE");
        }
        return citaRepository.save(cita);
    }
}
