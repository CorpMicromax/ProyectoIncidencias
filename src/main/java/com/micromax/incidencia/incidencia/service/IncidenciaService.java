package com.micromax.incidencia.incidencia.service;

import com.micromax.incidencia.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.incidencia.dto.IncidenciaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IncidenciaService {

    List<Incidencia> getIncidencias();

    Incidencia getIncidencia(long id);

    void createIncidencia(IncidenciaDTO i);

}
