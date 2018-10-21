package com.micromax.incidencia.service;

import com.micromax.incidencia.domain.entities.incidencias.Incidencia;

import java.util.List;


public interface IncidenciaService {

    List<Incidencia> getIncidencias();

    Incidencia getIncidencia(long id);

    void createIncidencia(Incidencia i, String username);

    Incidencia getIncidenciaById(long id);
}
