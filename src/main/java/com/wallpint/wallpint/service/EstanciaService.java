package com.wallpint.wallpint.service;

import com.wallpint.wallpint.model.Estancia;
import com.wallpint.wallpint.repository.EstanciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Esta clase ...
 *
 * @Autor: Javier Raposo Huelva
 * @Version: 2026:04
 */
@Service
public class EstanciaService {

    private final EstanciaRepository estanciaRepository;

    public EstanciaService(EstanciaRepository estanciaRepository) {
        this.estanciaRepository = estanciaRepository;
    }

    // Listar todas las estancias
    public List<Estancia> obtenerTodas() {
        return estanciaRepository.findAll();
    }

    // Guardar una nueva estancia
    public Estancia guardarEstancia(Estancia estancia) {
        return estanciaRepository.save(estancia);
    }
}
