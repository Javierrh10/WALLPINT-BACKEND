package com.wallpint.wallpint.model;

import jakarta.persistence.*;

/**
 * Esta clase ...
 *
 * @Autor: Javier Raposo Huelva
 * @Version: 2026:04
 */
@Entity
@Table(name = "estancias")
public class Estancia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre; // Ej: "Salón", "Dormitorio Principal", "Cocina"

    @Column(nullable = false)
    private Double metrosCuadrados;

    @Column(nullable = false)
    private String estadoParedes; // Ej: "BUEN ESTADO", "NECESITA REPARACIÓN", "CON HUMEDAD"

    @Column(nullable = false)
    private String color;

    // --- RELACIÓN CON PRESUPUESTO ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presupuesto_id", nullable = false)
    private Presupuesto presupuesto;

    // =============== Constructores ===============
    public Estancia() {}

    // =============== Getters y Setters ===============
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }
    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getEstadoParedes() {
        return estadoParedes;
    }
    public void setEstadoParedes(String estadoParedes) {
        this.estadoParedes = estadoParedes;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public Double getMetrosCuadrados() {
        return metrosCuadrados;
    }
    public void setMetrosCuadrados(Double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
