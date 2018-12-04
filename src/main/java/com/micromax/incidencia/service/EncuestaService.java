package com.micromax.incidencia.service;

import com.micromax.incidencia.domain.entities.Encuesta;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Usuario;

import java.util.List;

public interface EncuestaService {

    Encuesta getEncuestaByUsuario (long idEncuesta, boolean h, Usuario usuario);

    List<Encuesta> ListEncuestaByUsuario (Usuario usuario, boolean h);

    public Encuesta crearEncuesta (Usuario usuario, Incidencia incidencia, Encuesta encuesta);

}
