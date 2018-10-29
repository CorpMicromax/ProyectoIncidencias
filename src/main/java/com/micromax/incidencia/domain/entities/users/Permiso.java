package com.micromax.incidencia.domain.entities.users;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@Table(name = "permiso")
public class Permiso implements Serializable {

    @Transient
    private static final long serialVersionUID = 7L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_permiso")
    private Integer idPermiso;

    @Column(name = "nombre")
    private String nombre;

    @ManyToMany(mappedBy = "permisos")
    private Collection<Rol> roles;
}
