package com.wallpint.wallpint.service;

import com.wallpint.wallpint.model.EstadoPresupuesto;
import com.wallpint.wallpint.model.Presupuesto;
import com.wallpint.wallpint.repository.PresupuestoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Esta clase
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

    // Buscar presupuesto por su referencia única
    public List<Presupuesto> obtenerPorPintor(Long pintorId) {
        return presupuestoRepository.findByPintorId(pintorId);
    }

    // Buscar presupuesto por su referencia única y estado
    public List<Presupuesto> obtenerPorPintorYEstado(Long pintorId, String estado) {
        return presupuestoRepository.findByPintorIdAndEstado(pintorId, estado);
    }

    // Registrar un nuevo presupuesto
    public Presupuesto guardarPresupuesto(Presupuesto presupuesto) {
        if (presupuesto.getFechaCreacion() == null) {
            presupuesto.setFechaCreacion(LocalDateTime.now()); // Establece la fecha de creación al momento actual
        }
        if (presupuesto.getEstado() == null) {
            presupuesto.setEstado(EstadoPresupuesto.PENDIENTE); // Por defecto, el presupuesto se registra como pendiente
        }
        if (presupuesto.getReferencia() == null || presupuesto.getReferencia().isEmpty()) {
            // Genera una referencia única para el presupuesto (ej: "PRES-2026-0001")
            String numeroAleatorio = String.format("%04d", (int)(Math.random() * 10000)); // Genera un número aleatorio de 4 dígitos
            String referenciaUnica = "PRES-" + LocalDateTime.now().getYear() + "-" + numeroAleatorio;
            presupuesto.setReferencia(referenciaUnica);
        }
        return presupuestoRepository.save(presupuesto);
    }

    // Actualizar el estado y el precio de un presupuesto
    public Presupuesto actualizarEstadoYPrecio(Long id, EstadoPresupuesto nuevoEstado, Double nuevoPrecio) {
        Presupuesto presupuestoExistente = obtenerPorId(id);

        if (presupuestoExistente != null) {
            presupuestoExistente.setEstado(nuevoEstado);

            // Solo actualizamos el precio si el pintor ha enviado uno
            if (nuevoPrecio != null) {
                presupuestoExistente.setTotal(nuevoPrecio);
            }

            return presupuestoRepository.save(presupuestoExistente);
        }
        return null;
    }
}
