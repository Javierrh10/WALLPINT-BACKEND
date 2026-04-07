package com.wallpint.wallpint.service;

import com.wallpint.wallpint.model.Pintor;
import com.wallpint.wallpint.repository.PintorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Esta clase ...
 *
 * @Autor: Javier Raposo Huelva
 * @Version: 2026:04
 */
@Service
public class PintorService {
    private final PintorRepository pintorRepository;

    // ============== Constructor para inyectar el repositorio ===============
    public PintorService(PintorRepository pintorRepository) {
        this.pintorRepository = pintorRepository;
    }

    // Método para listar todos los pintores
    public List<Pintor> obtenerTodos() {
        return pintorRepository.findAll();
    }

    // Listar solo los pintores que están activos
    public List<Pintor> obtenerActivos() {
        return pintorRepository.findByActivoTrue();
    }

    // Registrar un nuevo pintor
    public Pintor guardarPintor(Pintor pintor) {
        if (pintor.getActivo() == null) {
            pintor.setActivo(true); // Por defecto, el pintor se registra como activo
        }

        return pintorRepository.save(pintor);
    }
}
