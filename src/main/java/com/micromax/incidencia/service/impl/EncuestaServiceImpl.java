package com.micromax.incidencia.service.impl;

import com.micromax.incidencia.domain.entities.Encuesta;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.repository.EncuestaRepository;
import com.micromax.incidencia.service.EncuestaService;
import com.micromax.incidencia.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j

public class EncuestaServiceImpl implements EncuestaService {

    @Autowired
    private EncuestaRepository encuestaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Encuesta getEncuestaByUsuario(long id, boolean h, Usuario usuario) {
        return encuestaRepository.findEncuestaByIdEncuestaAndHabilitadoAndUsuario(id,h,usuario);
    }

    @Override
    public List<Encuesta> ListEncuestaByUsuario(Usuario usuario, boolean h) {
        return encuestaRepository.findAllByUsuarioAndHabilitado(usuario,h);
    }

    @Override
    public Encuesta crearEncuesta(Usuario usuario, Incidencia incidencia, Encuesta encuesta) {
        encuesta.setUsuario(usuario);
        return encuestaRepository.save(encuesta);

    }
}
