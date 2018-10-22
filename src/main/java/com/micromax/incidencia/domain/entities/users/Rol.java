package com.micromax.incidencia.domain.entities.users;

import com.micromax.incidencia.domain.Desactivable;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Rol extends Desactivable {
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
