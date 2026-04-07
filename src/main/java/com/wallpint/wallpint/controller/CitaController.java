package com.wallpint.wallpint.controller;

import com.wallpint.wallpint.model.Cita;
import com.wallpint.wallpint.service.CitaService;
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
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;


    // ============== Constructor para inyectar el servicio ===============
    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    // ============== Métodos para el manejo de peticiones HTTP de las citas ===============

    // GET: http://localhost:8080/api/citas
    @GetMapping
    public ResponseEntity<List<Cita>> obtenerTodasLasCitas() {
        return new ResponseEntity<>(citaService.obtenerTodas(), HttpStatus.OK);
    }

    // POST: http://localhost:8080/api/citas
    @PostMapping
    public ResponseEntity<Cita> crearCita(@RequestBody Cita cita) {
        Cita nuevaCita = citaService.guardarCita(cita);
        return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
    }
}
