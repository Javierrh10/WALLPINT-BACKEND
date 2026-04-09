package com.wallpint.wallpint.service;

import com.wallpint.wallpint.model.Presupuesto;
import com.wallpint.wallpint.repository.PresupuestoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Esta clase ...
 *
 * @Autor: Javier Raposo Huelva
 * @Version: 2026:04
 */
@Service
public class PresupuestoService {

    private final PresupuestoRepository presupuestoRepository;

    // =============== Constructor para inyectar el servicio ===============
    public PresupuestoService(PresupuestoRepository presupuestoRepository) {
        this.presupuestoRepository = presupuestoRepository;
    }

    // =============== Métodos para el manejo de la lógica relacionada con los presupuestos ===============

    // Listar todos los presupuestos
    public List<Presupuesto> obtenerTodos() {
        return presupuestoRepository.findAll();
    }

    // Buscar presupuesto de un cliente concreto
    public List<Presupuesto> obtenerPorCliente(Long clienteId) {
        return presupuestoRepository.findByClienteId(clienteId);
    }

    // Buscar presupuesto por su ID
    public Presupuesto obtenerPorId(Long id) {
        return presupuestoRepository.findById(id).orElse(null);
    }

    // Registrar un nuevo presupuesto
    public Presupuesto guardarPresupuesto(Presupuesto presupuesto) {
        if (presupuesto.getFechaCreacion() == null) {
            presupuesto.setFechaCreacion(LocalDateTime.now()); // Establece la fecha de creación al momento actual
        }
        if (presupuesto.getEstado() == null || presupuesto.getEstado().isEmpty()) {
            presupuesto.setEstado("PENDIENTE"); // Por defecto, el presupuesto se registra como pendiente
        }
        return presupuestoRepository.save(presupuesto);
    }
}
