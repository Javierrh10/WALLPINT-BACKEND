package com.wallpint.wallpint.controller;

import com.wallpint.wallpint.model.EstadoPresupuesto;
import com.wallpint.wallpint.model.Presupuesto;
import com.wallpint.wallpint.service.PresupuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    private final PresupuestoService presupuestoService;

    // ============== Constructor para inyectar el servicio ===============
    public PresupuestoController(PresupuestoService presupuestoService) {
        this.presupuestoService = presupuestoService;
    }

    // ============== Métodos para el manejo de peticiones HTTP de los presupuestos ===============

    // GET: http://localhost:8080/api/presupuestos
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Presupuesto>> obtenerTodosLosPresupuestos() {
        return new ResponseEntity<>(presupuestoService.obtenerTodos(), HttpStatus.OK);
    }

    // GET: http://localhost:8080/api/presupuestos/cliente/1
    @PreAuthorize("hasAnyRole('CLIENTE', 'ADMIN')") // Solo los usuarios con rol CLIENTE o ADMIN pueden acceder
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Presupuesto>> obtenerPresupuestosPorCliente(@PathVariable Long clienteId) {
        return new ResponseEntity<>(presupuestoService.obtenerPorCliente(clienteId), HttpStatus.OK);
    }

    // GET: http://localhost:8080/api/presupuestos/pintor/1
    @PreAuthorize("hasAnyRole('PINTOR', 'ADMIN')") // Solo los usuarios con rol PINTOR o ADMIN pueden acceder
    @GetMapping("/pintor/{pintorId}")
    public ResponseEntity<List<Presupuesto>> obtenerPresupuestosPorPintor(@PathVariable Long pintorId) {
        return new ResponseEntity<>(presupuestoService.obtenerPorPintor(pintorId), HttpStatus.OK);
    }

    // GET: http://localhost:8080/api/presupuestos/{id}
    @PreAuthorize("hasAnyRole('CLIENTE', 'PINTOR', 'ADMIN')") // Solo los usuarios con rol CLIENTE, PINTOR o ADMIN
    @GetMapping("/{id}")
    public ResponseEntity<Presupuesto> obtenerPresupuestoPorId(@PathVariable Long id) {
        Presupuesto presupuesto = presupuestoService.obtenerPorId(id);
        if (presupuesto != null) {
            return new ResponseEntity<>(presupuesto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Devuelve 404 si el ID no existe
        }
    }

    // POST: http://localhost:8080/api/presupuestos
    @PreAuthorize("hasAnyRole('CLIENTE', 'ADMIN')") // Solo los usuarios con rol CLIENTE o ADMIN pueden crear presupuestos
    @PostMapping
    public ResponseEntity<Presupuesto> crearPresupuesto(@RequestBody Presupuesto presupuesto) {
        Presupuesto nuevoPresupuesto = presupuestoService.guardarPresupuesto(presupuesto);
        return new ResponseEntity<>(nuevoPresupuesto, HttpStatus.CREATED);
    }

    // PUT: http://localhost:8080/api/presupuestos/1/responder?estado=ACEPTADO&precio=150.0
    @PreAuthorize("hasAnyRole('PINTOR', 'ADMIN')") // Protegido: Solo los pintores responden con el precio
    @PutMapping("/{id}/responder")
    public ResponseEntity<Presupuesto> responderPresupuesto(
            @PathVariable Long id,
            @RequestParam EstadoPresupuesto estado,
            @RequestParam(required = false) Double precio) {

        Presupuesto actualizado = presupuestoService.actualizarEstadoYPrecio(id, estado, precio);

        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PUT: http://localhost:8080/api/presupuestos/3/decision?estado=APROBADO
    @PreAuthorize("hasAnyRole('CLIENTE', 'ADMIN')") // Solo el cliente decide si acepta el precio final
    @PutMapping("/{id}/decision")
    public ResponseEntity<Presupuesto> decisionCliente(
            @PathVariable Long id,
            @RequestParam EstadoPresupuesto estado) {

        // Aquí reutilizamos tu método de actualizar estado (le pasamos null al precio para no cambiarlo)
        Presupuesto actualizado = presupuestoService.actualizarEstadoYPrecio(id, estado, null);

        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
