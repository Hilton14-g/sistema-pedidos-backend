package com.picanteria.sistema_pedidos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public enum Rol {
        ADMIN, COCINA, MESERO
    }

    // --- MÉTODOS MANUALES (PARA QUE NO SALGA ROJO) ---
    public String getPassword() {
        return password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public Rol getRol() {
        return rol;
    }
}