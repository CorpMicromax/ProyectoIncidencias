package com.micromax.incidencia.incidencia.domain.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Tarea {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "id_tarea")
    private long idTarea;

    private String descripcion;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private String titulo;
}
