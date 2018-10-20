package com.micromax.incidencia.incidencia.domain;

import lombok.Data;

import java.util.Collection;

@Data
public class Tecnico extends Usuario {

    private int capacidad;


    private Collection<Incidencia> asignaciones;
}
