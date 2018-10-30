package com.micromax.incidencia.service;

import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.dto.IncidenciaDTO;

import java.util.List;


public interface IncidenciaService {

    List<Incidencia> getIncidencias();

    void guardarIncidencia(IncidenciaDTO i, String username);

    void actualizarIncidencia(IncidenciaDTO i);

    Incidencia getIncidenciaById(String id);

    boolean borrarIncidencia(String id);
}

