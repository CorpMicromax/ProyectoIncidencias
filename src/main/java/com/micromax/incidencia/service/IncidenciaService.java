package com.micromax.incidencia.service;

import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.dto.IncidenciaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IncidenciaService {

    List<Incidencia> getIncidencias();

    Incidencia getIncidencia(long id);

    void createIncidencia(IncidenciaDTO i);

}
