package com.micromax.incidencia.domain.entities.users;

import com.micromax.incidencia.domain.Desactivable;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Privilegio extends Desactivable {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id_privilegio")
        private Integer idPrivilegio;

        private String nombre;

        @ManyToMany(mappedBy = "privilegios")
        private Collection<Rol> roles;
    }
