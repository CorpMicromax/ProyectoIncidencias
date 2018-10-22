package com.micromax.incidencia.service;

import com.micromax.incidencia.domain.entities.incidencias.Incidencia;

import java.util.List;


public interface IncidenciaService {

    List<Incidencia> getIncidencias();

    void guardarIncidencia(Incidencia i, String username);

    Incidencia getIncidenciaById(long id);

    boolean borrarIncidencia(Long id);
}

