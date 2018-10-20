package com.micromax.incidencia.incidencia.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Privilegio {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String nombre;

        @ManyToMany(mappedBy = "privilegios")
        private Collection<Rol> roles;
    }
