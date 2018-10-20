package com.micromax.incidencia.incidencia.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String nombres;
    private String apellidos;
    private String email;
    private String username;
    private String password;
    private boolean enabled;
    private boolean tokenExpired;

    @ManyToMany
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(
                    name = "id_usuario", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "rol_id", referencedColumnName = "id"))
    private Collection<Rol> roles;

}
