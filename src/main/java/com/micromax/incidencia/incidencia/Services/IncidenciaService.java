package com.micromax.incidencia.incidencia.Services;

import com.micromax.incidencia.incidencia.dao.Incidencia;

import java.util.List;


public interface IncidenciaService {

    List<Incidencia> getIncidencias();

    Incidencia getIncidencia(long id);

    void createIncidencia(Incidencia i);

}
