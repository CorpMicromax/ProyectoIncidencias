package com.micromax.incidencia.domain.entities.users;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
public class Privilegio implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id_privilegio")
        private Integer idPrivilegio;

        private String nombre;

        @ManyToMany(mappedBy = "privilegios")
        private Collection<Rol> roles;
    }
