package com.micromax.incidencia.incidencia.domain.entities.users;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
public class Rol implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_rol")
    private Integer idRol;

    private String nombre;
    @ManyToMany(mappedBy = "roles")
    private Collection<Usuario> usuarios;

    @ManyToMany
    @JoinTable(
            name = "roles_privilegios",
            joinColumns = @JoinColumn(
                    name = "id_rol", referencedColumnName = "id_Rol"),
            inverseJoinColumns = @JoinColumn(
                    name = "id_privilegio", referencedColumnName = "id_Privilegio"))
    private Collection<Privilegio> privilegios;
}
