package com.micromax.incidencia.domain.entities.users;

import com.micromax.incidencia.domain.Desactivable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Permiso extends Desactivable implements Serializable {

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
