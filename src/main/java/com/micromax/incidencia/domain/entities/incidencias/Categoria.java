package com.micromax.incidencia.domain.entities.incidencias;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String nombre;

    private int nivel;

    @OneToOne
    private Categoria padre;
}
