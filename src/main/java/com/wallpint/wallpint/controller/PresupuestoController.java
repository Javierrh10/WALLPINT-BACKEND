package com.wallpint.wallpint.controller;

import com.wallpint.wallpint.model.Presupuesto;
import com.wallpint.wallpint.service.PresupuestoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Esta clase ...
 *
 * @Autor: Javier Raposo Huelva
 * @Version: 2026:04
 */
@RestController
@RequestMapping("/api/presupuestos")
public class PresupuestoController {

    private final PresupuestoService presupuestoService;

    // ============== Constructor para inyectar el servicio ===============
    public PresupuestoController(PresupuestoService presupuestoService) {
        this.presupuestoService = presupuestoService;
    }

    // ============== Métodos para el manejo de peticiones HTTP de los presupuestos ===============

    // GET: http://localhost:8080/api/presupuestos
    @GetMapping
    public ResponseEntity<List<Presupuesto>> obtenerTodosLosPresupuestos() {
        return new ResponseEntity<>(presupuestoService.obtenerTodos(), HttpStatus.OK);
    }

    // GET: http://localhost:8080/api/presupuestos/cliente/1
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Presupuesto>> obtenerPresupuestosPorCliente(@PathVariable Long clienteId) {
        return new ResponseEntity<>(presupuestoService.obtenerPorCliente(clienteId), HttpStatus.OK);
    }

    // POST: http://localhost:8080/api/presupuestos
    @PostMapping
    public ResponseEntity<Presupuesto> crearPresupuesto(@RequestBody Presupuesto presupuesto) {
        Presupuesto nuevoPresupuesto = presupuestoService.guardarPresupuesto(presupuesto);
        return new ResponseEntity<>(nuevoPresupuesto, HttpStatus.CREATED);
    }
}
