package com.wallpint.wallpint.controller;

import com.wallpint.wallpint.dto.AuthResponse;
import com.wallpint.wallpint.dto.LoginRequest;
import com.wallpint.wallpint.model.Cliente;
import com.wallpint.wallpint.model.Pintor;
import com.wallpint.wallpint.model.Usuario;
import com.wallpint.wallpint.repository.UsuarioRepository;
import com.wallpint.wallpint.security.JwtUtil;
import com.wallpint.wallpint.service.ClienteService;
import com.wallpint.wallpint.service.PintorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Esta clase se encarga de manejar las solicitudes de autenticación y registro de usuarios.
 * Contiene rutas para que los clientes y pintores puedan registrarse, así como una ruta
 * para que ambos tipos de usuarios puedan iniciar sesión y obtener un token JWT para autenticarse
 * en futuras solicitudes a la API.
 *
 * @author : Javier Raposo Huelva
 * @version : 2026:04
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PintorService pintorService;

    // ================== RUTA DE LOGIN ==================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(loginRequest.getEmail());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), usuario.getPasswordHash())) {
                String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getRol().name());
                return ResponseEntity.ok(new AuthResponse(token, usuario.getRol().name()));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }

    // ================== RUTAS DE REGISTRO ==================

    @PostMapping("/registro/cliente")
    public ResponseEntity<?> registrarCliente(@RequestBody Cliente cliente) {
        // Comprobamos si el email ya existe para no tener duplicados
        if (usuarioRepository.existsByEmail(cliente.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El email ya está en uso");
        }

        Cliente nuevoCliente = clienteService.guardarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    @PostMapping("/registro/pintor")
    public ResponseEntity<?> registrarPintor(@RequestBody Pintor pintor) {
        if (usuarioRepository.existsByEmail(pintor.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El email ya está en uso");
        }

        Pintor nuevoPintor = pintorService.guardarPintor(pintor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPintor);
    }
}
