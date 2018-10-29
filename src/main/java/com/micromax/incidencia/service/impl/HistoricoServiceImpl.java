package com.micromax.incidencia.service.impl;

import com.micromax.incidencia.domain.entities.Historico;
import com.micromax.incidencia.domain.entities.TipoCambio;
import com.micromax.incidencia.repository.HistoricoRepository;
import com.micromax.incidencia.service.HistoricoService;
import com.micromax.incidencia.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Slf4j

public class HistoricoServiceImpl implements HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Historico getHistoricoByIdAndIncidencia(long id, String idIncidencia) {
        return historicoRepository.findHistoricoByIdHistoricoAndIncidencia(id,idIncidencia).orElse(null);
    }

    @Override
    public Historico findHistoricoByIncidenciaAndUsuario(long id, String idIncidencia, long idUser) {
        return historicoRepository.findHistoricoByIdHistoricoAndIncidenciaAndUsuarioResponsable(id,idIncidencia,idUser).orElse(null);
    }

    @Override
    public Collection<Historico> getHistoricoByIncidencia(String idIncidencia) {
        return historicoRepository.findAllByIncidencia(idIncidencia);
    }

    @Override
    public Collection<Historico> getHistoricoByUsuario(long idUser, String idIncidencia) {
        return historicoRepository.findAllByIncidenciaAndUsuarioResponsable(idIncidencia, idUser);
    }
    // Pendiente
    @Override
    public List<TipoCambio> getTipoCambio() {
        return null;
    }

    @Override
    public void guardarHistorico(Historico historico, long idUsuario) {
        historico.setUsuarioResponsable(usuarioService.getUsuarioById(idUsuario));
        historico.setMomento(Date.from(LocalDateTime.now().toInstant(ZoneOffset.ofHours(0))));
        historico = historicoRepository.save(historico);
    }
}
