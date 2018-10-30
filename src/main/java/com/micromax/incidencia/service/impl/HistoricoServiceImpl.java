package com.micromax.incidencia.service.impl;

import com.micromax.incidencia.domain.entities.Historico;
import com.micromax.incidencia.domain.entities.incidencias.Comentario;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Usuario;
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

@Service
@Slf4j
public class HistoricoServiceImpl implements HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Historico getHistoricoByIdAndIncidencia(long id, Incidencia incidencia) {
        return historicoRepository.findHistoricoByIdHistoricoAndIncidencia(id,incidencia).orElse(null);
    }

    @Override
    public Historico findHistoricoByIncidenciaAndUsuario(long id, Incidencia incidencia, Usuario usuario) {
        return historicoRepository.findHistoricoByIdHistoricoAndIncidenciaAndUsuarioResponsable(id,incidencia,usuario).orElse(null);
    }

    @Override
    public Historico findHistoricoByComentarioAndIncidencia(long id, Incidencia incidencia, Comentario comentario) {
        return historicoRepository.findHistoricoByComentarioAndIncidencia(id,incidencia,comentario).orElse(null);
    }

    @Override
    public Collection<Historico> getHistoricoByIncidencia(Incidencia idIncidencia) {
        return historicoRepository.findAllByIncidencia(idIncidencia);
    }

    @Override
    public Collection<Historico> getHistoricoByUsuario(Usuario idUser, Incidencia idIncidencia) {
        return historicoRepository.findAllByIncidenciaAndUsuarioResponsable(idIncidencia, idUser);
    }
     @Override
    public void guardarHistorico(Historico historico, long idUsuario) {
        historico.setUsuarioResponsable(usuarioService.getUsuarioById(idUsuario));
        historico.setMomento(Date.from(LocalDateTime.now().toInstant(ZoneOffset.ofHours(0))));
        historicoRepository.save(historico);
    }
}
