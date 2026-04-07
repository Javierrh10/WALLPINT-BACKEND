package com.wallpint.wallpint.controller;

import com.wallpint.wallpint.model.Pintor;
import com.wallpint.wallpint.service.PintorService;
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
@RequestMapping("/api/pintores")
public class PintorController {

    private final PintorService pintorService;

    // ============== Constructor para inyectar el servicio ===============
    public PintorController(PintorService pintorService) {
        this.pintorService = pintorService;
    }

    // ============== Métodos para el manejo de peticiones HTTP de los pintores ===============

    // GET: http://localhost:8080/api/pintores
    @GetMapping
    public ResponseEntity<List<Pintor>> obtenerTodosLosPintores() {
        return new ResponseEntity<>(pintorService.obtenerTodos(), HttpStatus.OK);
    }

    // GET: http://localhost:8080/api/pintores/activos
    @GetMapping("/activos")
    public ResponseEntity<List<Pintor>> obtenerPintoresActivos() {
        return new ResponseEntity<>(pintorService.obtenerActivos(), HttpStatus.OK);
    }

    // POST: http://localhost:8080/api/pintores
    @PostMapping
    public ResponseEntity<Pintor> crearPintor(@RequestBody Pintor pintor) {
        Pintor nuevoPintor = pintorService.guardarPintor(pintor);
        return new ResponseEntity<>(nuevoPintor, HttpStatus.CREATED);
    }
}
