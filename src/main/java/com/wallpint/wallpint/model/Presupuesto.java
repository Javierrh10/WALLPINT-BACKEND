package com.wallpint.wallpint.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Esta clase ...
 *
 * @Autor: Javier Raposo Huelva
 * @Version: 2026:04
 */
@Entity
@Table(name = "presupuestos")
public class Presupuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String referencia; // Ej: "PRES-2026-0001"

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = true)
    private Double total;

    @Column(nullable = false, length = 1000)
    private String descripcion; // Detalles del presupuesto, como las estancias incluidas, materiales, etc.

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPresupuesto estado; // Ej: "PENDIENTE", "ACEPTADO", "RECHAZADO"

    // --- RELACIÓN CON CLIENTE ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // --- RELACIÓN CON PINTOR ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pintor_id", nullable = false)
    private Pintor pintor; // El pintor asignado al presupuesto, puede ser null

    // =============== Constructores ===============
    // Constructor vacío
    public Presupuesto() {
        this.fechaCreacion = LocalDateTime.now();
        this.estado = EstadoPresupuesto.PENDIENTE; // Por defecto, el presupuesto se crea en estado PENDIENTE
    }

    // =============== Getters y Setters ===============

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public EstadoPresupuesto getEstado() {
        return estado;
    }

    public void setEstado(EstadoPresupuesto estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pintor getPintor() {
        return pintor;
    }

    public void setPintor(Pintor pintor) {
        this.pintor = pintor;
    }
}
