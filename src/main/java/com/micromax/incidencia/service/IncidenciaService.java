package com.micromax.incidencia.service;

import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.dto.IncidenciaDTO;

import java.util.List;


public interface IncidenciaService {

    List<Incidencia> getIncidencias();

    Incidencia getIncidencia(long id);

    void createIncidencia(IncidenciaDTO i, String username);

}
