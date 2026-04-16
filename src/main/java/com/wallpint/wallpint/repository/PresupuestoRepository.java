package com.wallpint.wallpint.repository;

import com.wallpint.wallpint.model.Presupuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Esta clase ...
 *
 * @Autor: Javier Raposo Huelva
 * @Version: 2026:04
 */
@Repository
public interface PresupuestoRepository extends JpaRepository<Presupuesto, Long> {
    // Busca todos los presupuestos de un cliente específico a través de su ID
    List<Presupuesto> findByClienteId(Long clienteId);

    // Método para buscar un presupuesto por su referencia única (ej: "PRES-2026-0001")
    Optional<Presupuesto> findByReferencia(String referencia);

    // Busca los presupuestos de un pintor específico a través de su ID
    List<Presupuesto> findByPintorId(Long pintorId);

    // Busca los presupuestos de un pintor específico pero filtrado por el estado.
    List<Presupuesto> findByPintorIdAndEstado(Long pintorId, String estado);
}
