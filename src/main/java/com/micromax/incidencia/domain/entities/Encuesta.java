package com.micromax.incidencia.domain.entities;

import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Encuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_encuesta")
    private long idEncuesta;
    private byte puntaje;
    private String mensaje;

    @OneToOne
    private Incidencia incidencia;
}
