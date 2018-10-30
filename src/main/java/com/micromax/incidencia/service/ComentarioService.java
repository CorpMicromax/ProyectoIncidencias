package com.micromax.incidencia.service;

import com.micromax.incidencia.domain.entities.incidencias.Comentario;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Usuario;

import java.util.Collection;
import java.util.Date;


public interface ComentarioService {

    Comentario getComentarioByIdAndIncidencia(long id, Incidencia idIncidencia);

    Comentario getComentarioByIncidenciaAndUsuarioAndFecha(Incidencia idIncidencia, Usuario idUsuario, Date fechaCreacion);

    Collection<Comentario> getComentarioByIncidencia(Incidencia idIncidencia );

    public void guardarComentario(Comentario comentario, long idUsuario);

}
