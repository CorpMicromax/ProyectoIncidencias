package com.micromax.incidencia.incidencia.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Encuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private byte puntaje;
    private String mensaje;

    @OneToOne
    private Incidencia incidencia;
}
