package com.micromax.incidencia.incidencia.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CategoriaNivelDos{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nombre")
    private String nombre;
}
