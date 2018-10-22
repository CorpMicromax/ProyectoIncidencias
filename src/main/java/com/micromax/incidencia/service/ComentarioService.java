package com.micromax.incidencia.service;

import com.micromax.incidencia.domain.entities.incidencias.Comentario;


import java.util.List;


public interface ComentarioService {

    List<Comentario> getComentario();

    Comentario getComentario(long id);

    void createComentario(Comentario i, String username);

}
