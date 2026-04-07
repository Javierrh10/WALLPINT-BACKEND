package com.wallpint.wallpint.repository;

import com.wallpint.wallpint.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Esta clase ...
 *
 * @Autor: Javier Raposo Huelva
 * @Version: 2026:04
 */
@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    // Para listar todas las citas de un cliente
    List<Cita> findByClienteId(Long clienteId);

    // Para el calendario: buscar citas en un rango de fechas
    List<Cita> findByFechaHoraBetween(LocalDate fechaInicio, LocalDate fechaFin);

    // Para filtrar por estado (Ej: "PROGRAMADA", "CANCELADA", "COMPLETADA", "EN_CURSO")
    List<Cita> findByEstado(String estado);
}
