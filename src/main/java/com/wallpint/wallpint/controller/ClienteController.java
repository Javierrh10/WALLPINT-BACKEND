package com.wallpint.wallpint.controller;

import com.wallpint.wallpint.model.Cliente;
import com.wallpint.wallpint.service.ClienteService;
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
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    // ============== Constructor para inyectar el servicio ===============
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // ============== Métodos para el manejo de peticiones HTTP de los clientes ===============

    // GET: http://localhost:8080/api/clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteService.obtenerTodos();
        return new ResponseEntity<>(clientes, HttpStatus.OK); // Devuelve un código 200 OK
    }

    // POST: http://localhost:8080/api/clientes
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.guardarCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED); // Devuelve un código 201 CREATED
    }
}
