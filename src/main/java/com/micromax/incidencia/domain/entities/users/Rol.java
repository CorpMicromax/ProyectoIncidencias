package com.micromax.incidencia.domain.entities.users;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "rol")
public class Rol implements Serializable {

    @Transient
    private static final long serialVersionUID = 8L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol", unique = true, nullable = false, precision = 2)
    private int idRol;

    @Column(name = "nombre")
    private String nombre;

    public Rol(String nombre){};

    public Rol(){};

    public static final Rol adminRol = new Rol("ADMIN_ROLE");
}
