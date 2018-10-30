package com.micromax.incidencia.service.impl;


import com.micromax.incidencia.domain.entities.Historico;
import com.micromax.incidencia.domain.entities.incidencias.Comentario;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.repository.ComentarioRepository;
import com.micromax.incidencia.repository.HistoricoRepository;
import com.micromax.incidencia.repository.IncidenciaRepository;
import com.micromax.incidencia.repository.UsuarioRepository;
import com.micromax.incidencia.service.ComentarioService;
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

public class ComentarioServiceImpl implements ComentarioService {

    @Autowired ComentarioRepository comentarioRepository;

    @Autowired HistoricoRepository historicoRepository;

    @Autowired IncidenciaRepository incidenciaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Comentario getComentarioByIdAndIncidencia(long id, Incidencia idIncidencia) {
        return comentarioRepository.findComentarioByIdComentarioAndIncidencia(id, idIncidencia).orElse(null);
    }

    @Override
    public Comentario getComentarioByIncidenciaAndUsuarioAndFecha(Incidencia idIncidencia, Usuario idUsuario, Date fechaCreacion) {
        return comentarioRepository.findComentarioByIncidenciaAndAutorAndCreacion(idIncidencia,idUsuario, fechaCreacion).orElse(null);
    }

    @Override
    public Collection<Comentario> getComentarioByIncidencia(Incidencia idIncidencia) {
        return comentarioRepository.findAllByIncidencia(idIncidencia);
    }

    @Override
    public void guardarComentario(Comentario comentario, long idUsuario) {
        comentario.setAutor(usuarioService.getUsuarioById(idUsuario));
        comentario.setCreacion(Date.from(LocalDateTime.now().toInstant(ZoneOffset.ofHours(0))));
        comentarioRepository.save(comentario);
    }
}
