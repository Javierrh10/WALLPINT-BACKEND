package com.wallpint.wallpint.dto;

/**
 * Esta clase representa la respuesta que se envía al cliente después de un intento de autenticación exitoso.
 * Contiene el token JWT generado que el cliente debe usar para autenticar futuras solicitudes a la API.
 *
 * @author : Javier Raposo Huelva
 * @version : 2026:04
 */
public class AuthResponse {

    private String token;

    // =============== Constructores ===============
    public AuthResponse(String token) {
        this.token = token;
    }

    // =============== Getters y Setters ===============
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
