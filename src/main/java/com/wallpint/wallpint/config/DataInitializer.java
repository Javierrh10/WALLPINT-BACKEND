package com.wallpint.wallpint.config;

import com.wallpint.wallpint.model.Rol;
import com.wallpint.wallpint.model.Usuario;
import com.wallpint.wallpint.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Esta clase se encarga de inicializar la base de datos con un usuario administrador predeterminado.
 * Al arrancar la aplicación,
 *
 * @author : Javier Raposo Huelva
 * @version : 2026:04
 */
@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Comprobamos si el correo del admin ya existe en la base de datos
            if (!usuarioRepository.existsByEmail("admin@wallpint.com")) {

                Usuario admin = new Usuario();
                admin.setNombre("Admin");
                admin.setApellidos("Admin");
                admin.setEmail("admin@wallpint.com");
                admin.setTelefono("600123456");

                // Encriptamos la contraseña antes de guardarla
                admin.setPasswordHash(passwordEncoder.encode("1234"));
                admin.setRol(Rol.ADMIN);

                usuarioRepository.save(admin);

                System.out.println("Administrador creado con éxito: admin@wallpint.com / 1234");
            } else {
                System.out.println("El Administrador principal ya estaba en la base de datos.");
            }
        };
    }
}
