package com.micromax.incidencia.incidencia.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    @ManyToMany(mappedBy = "roles")
    private Collection<Usuario> usuarios;

    @ManyToMany
    @JoinTable(
            name = "roles_privilegios",
            joinColumns = @JoinColumn(
                    name = "id_rol", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "id_privilegio", referencedColumnName = "id"))
    private Collection<Privilegio> privilegios;
}
