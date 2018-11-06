package com.micromax.incidencia.service;

import com.micromax.incidencia.domain.entities.Historico;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Usuario;

import java.util.Collection;
import java.util.List;

public interface HistoricoService {

    Historico getHistoricoByIdAndIncidencia(long id, Incidencia idIncidencia);

    Historico findHistoricoByIncidenciaAndUsuario(long id,Incidencia idIncidencia, Usuario idUser);

  //  Historico findHistoricoByComentarioAndIncidencia(long id, Comentario idComentario, Incidencia idIncidencia);

    List<Historico> getHistoricoByIncidencia (Incidencia idIncidencia);

    Collection<Historico> getHistoricoByUsuario (Usuario idUser, Incidencia idIncidencia);

    public void guardarHistorico (Historico historico, Usuario user);

}
