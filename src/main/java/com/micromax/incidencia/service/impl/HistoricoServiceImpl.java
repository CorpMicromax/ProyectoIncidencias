package com.micromax.incidencia.service.impl;

import com.micromax.incidencia.domain.entities.Historico;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.repository.HistoricoRepository;
import com.micromax.incidencia.service.HistoricoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class HistoricoServiceImpl implements HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Override
    public Historico getHistoricoByIdAndIncidencia(long id, Incidencia incidencia) {
        return historicoRepository.findHistoricoByIdHistoricoAndIncidencia(id,incidencia).orElse(null);
    }

    @Override
    public Historico findHistoricoByIncidenciaAndUsuario(long id, Incidencia incidencia, Usuario usuario) {
        return historicoRepository.findHistoricoByIdHistoricoAndIncidenciaAndUsuarioResponsable(id,incidencia,usuario).orElse(null);
    }

    @Override
    public List<Historico> getHistoricoByIncidencia(Incidencia idIncidencia) {
        return historicoRepository.findAllByIncidencia(idIncidencia);
    }

    @Override
    public Collection<Historico> getHistoricoByUsuario(Usuario idUser, Incidencia idIncidencia) {
        return historicoRepository.findAllByIncidenciaAndUsuarioResponsable(idIncidencia, idUser);
    }
     @Override
    public void guardarHistorico(Historico historico, Usuario user) {
        historico.setUsuarioResponsable(user);
        historico.setMomento(new Date());
        historicoRepository.save(historico);
    }
}
