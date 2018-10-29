package com.micromax.incidencia.service;

import com.micromax.incidencia.domain.entities.Historico;
import com.micromax.incidencia.domain.entities.TipoCambio;

import java.util.Collection;
import java.util.List;

public interface HistoricoService {

    Historico getHistoricoByIdAndIncidencia(long id,String idIncidencia);

    Historico findHistoricoByIncidenciaAndUsuario(long id,String idIncidencia, long idUser);

    Collection<Historico> getHistoricoByIncidencia (String idIncidencia);

    Collection<Historico> getHistoricoByUsuario (long idUser, String idIncidencia);

    //Pendiente
    List<TipoCambio> getTipoCambio();

    public void guardarHistorico (Historico historico, long idUsuario);

}
