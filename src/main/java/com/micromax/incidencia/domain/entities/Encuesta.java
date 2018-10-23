package com.micromax.incidencia.domain.entities;

import com.micromax.incidencia.domain.Desactivable;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Encuesta extends Desactivable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_encuesta")
    private long idEncuesta;
    private byte puntaje;
    private String mensaje;
}
