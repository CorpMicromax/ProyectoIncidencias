package com.micromax.incidencia.incidencia.domain.entities.users;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


@Data
@Entity
@Inheritance
@DiscriminatorColumn(name = "tipo_usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_usuario")
    private long idUsuario;

    private String nombres;
    private String apellidos;
    private String email;
    private String username;
    private String password;
    private String direccion;
    private String telefono;
    private boolean enabled;
    private boolean tokenExpired;

    @ManyToMany
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(
                    name = "id_usuario", referencedColumnName = "id_usuario"),
            inverseJoinColumns = @JoinColumn(
                    name = "id_rol", referencedColumnName = "id_rol"))
    private Collection<Rol> roles;

}
