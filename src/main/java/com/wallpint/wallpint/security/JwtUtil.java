package com.wallpint.wallpint.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Esta clase ...
 *
 * @author : Javier Raposo Huelva
 * @version : 2026:04
 */
@Component
public class JwtUtil {

    private static final String SECRET_KEY_STRING = "EstaEsMiSuperClaveSecretaDeWallpint2026";

    // Convertimos el texto en una llave criptográfica
    private final Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());

    // Tiempo de validez del token: 24 horas (en milisegundos)
    private static final long EXPIRATION_TIME = 86400000;

    // Método para generar un token JWT a partir del email y el rol del usuario
    public String generateToken(String email, String rol) {
        return Jwts.builder()
                .setSubject(email) // El "asunto" del token, que en este caso es el email del usuario
                .claim("rol", rol) // Le pegamos una etiqueta con su rol (ADMIN, CLIENTE o PINTOR)
                .setIssuedAt(new Date()) // Fecha de creación
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Fecha de caducidad
                .signWith(secretKey, SignatureAlgorithm.HS256) // La firma digital de tu servidor
                .compact(); // Lo comprime en un texto (el Token JWT final)
    }

    // Método para sacar el email (subject) del token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Método para validar que el token es correcto y no está caducado
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false; // Si falla la firma o está caducado, devuelve false
        }
    }

    // Método para sacar el rol del usuario del token
    public String extractRol(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("rol", String.class);
    }
}
