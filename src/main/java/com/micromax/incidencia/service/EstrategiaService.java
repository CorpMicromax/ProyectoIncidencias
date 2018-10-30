package com.micromax.incidencia.service;

import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Tecnico;

import java.util.List;

public interface EstrategiaService {

    Tecnico ejecutarEstrategia(Incidencia incidencia, List<Tecnico> tecnicos);
}
