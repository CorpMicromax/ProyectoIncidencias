package com.micromax.incidencia.service;

import com.micromax.incidencia.domain.entities.Historico;
import com.micromax.incidencia.domain.entities.incidencias.Comentario;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Usuario;

import java.util.Collection;

public interface HistoricoService {

    Historico getHistoricoByIdAndIncidencia(long id, Incidencia idIncidencia);

    Historico findHistoricoByIncidenciaAndUsuario(long id,Incidencia idIncidencia, Usuario idUser);

    Historico findHistoricoByComentarioAndIncidencia(long id,Incidencia idIncidencia, Comentario idcomentario);

    Collection<Historico> getHistoricoByIncidencia (Incidencia idIncidencia);

    Collection<Historico> getHistoricoByUsuario (Usuario idUser, Incidencia idIncidencia);

    public void guardarHistorico (Historico historico, long idUsuario);

}
