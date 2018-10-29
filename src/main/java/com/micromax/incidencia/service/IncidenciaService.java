package com.micromax.incidencia.service;

import com.micromax.incidencia.domain.entities.incidencias.Incidencia;

import java.util.List;


public interface IncidenciaService {

    List<Incidencia> getIncidencias();

    void guardarIncidencia(Incidencia i, String username);

    void actualizarIncidencia(Incidencia i);

    Incidencia getIncidenciaById(String id);

    boolean borrarIncidencia(String id);
}

