package com.wallpint.wallpint.service;

import com.wallpint.wallpint.model.Estancia;
import com.wallpint.wallpint.model.Presupuesto;
import com.wallpint.wallpint.repository.EstanciaRepository;
import com.wallpint.wallpint.repository.PresupuestoRepository;
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
    private final PresupuestoRepository presupuestoRepository;

    public EstanciaService(EstanciaRepository estanciaRepository, PresupuestoRepository presupuestoRepository) {
        this.estanciaRepository = estanciaRepository;
        this.presupuestoRepository = presupuestoRepository;
    }

    // Listar todas las estancias
    public List<Estancia> obtenerTodas() {
        return estanciaRepository.findAll();
    }

    // Guardar una nueva estancia y actualizar el presupuesto asociado
    public Estancia guardarEstancia(Estancia estancia) {
        Estancia estanciaGuardada = estanciaRepository.save(estancia);

        if(estancia.getPresupuesto() != null && estancia.getPresupuesto().getId() != null){
            Presupuesto presupuesto = presupuestoRepository.findById(estancia.getPresupuesto().getId()).orElse(null);

            if (presupuesto != null) {
               double precioPorMetro = 12.0; // Precio fijo por metro cuadrado
               double costoDeEstaHabitacion = estanciaGuardada.getMetrosCuadrados() * precioPorMetro;

               double totalActual = presupuesto.getTotal() != null ? presupuesto.getTotal() : 0.0;

                presupuesto.setTotal(totalActual + costoDeEstaHabitacion);
                presupuestoRepository.save(presupuesto);
            }
        }

        return estanciaGuardada;
    }
}
