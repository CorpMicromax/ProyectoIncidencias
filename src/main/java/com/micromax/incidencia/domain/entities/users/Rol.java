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
@Table(name = "rol")
public class Rol extends Desactivable implements Serializable {

    @Transient
    private static final long serialVersionUID = 8L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol", unique = true, nullable = false, precision = 2)
    private Integer idRol;

    @Column(name = "nombre")
    private String nombre;

    @ManyToMany
    @JoinTable(
            name = "roles_privilegios",
            joinColumns = @JoinColumn(
                    name = "id_rol", referencedColumnName = "id_Rol"),
            inverseJoinColumns = @JoinColumn(
                    name = "id_permiso", referencedColumnName = "id_permiso"))
    private Collection<Permiso> permisos;
}
