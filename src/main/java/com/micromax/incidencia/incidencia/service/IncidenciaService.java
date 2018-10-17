package com.micromax.incidencia.incidencia.service;

import com.micromax.incidencia.incidencia.domain.Incidencia;

import java.util.List;


public interface IncidenciaService {

    List<Incidencia> getIncidencias();

    Incidencia getIncidencia(long id);

    void createIncidencia(Incidencia i);

}
