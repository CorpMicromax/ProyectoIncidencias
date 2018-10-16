package com.micromax.incidencia.incidencia.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Tarea {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;

    private String descripcion;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private String titulo;
}
