package com.wallpint.wallpint.service;

import com.wallpint.wallpint.model.Cliente;
import com.wallpint.wallpint.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Esta clase ...
 *
 * @Autor: Javier Raposo Huelva
 * @Version: 2026:04
 */
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    // Constructor para inyectar el repositorio
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Método para listar todos los clientes
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    // Método para guardar un nuevo cliente
    public Cliente guardarCliente(Cliente cliente) {
        cliente.setFechaRegistro(LocalDateTime.now()); // Establece la fecha de registro al momento actual
        return clienteRepository.save(cliente);
    }

    // Método para buscar un cliente por su email
    public Optional<Cliente> buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }
}
