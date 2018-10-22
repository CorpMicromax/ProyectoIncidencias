package com.micromax.incidencia.service.impl;

import com.micromax.incidencia.domain.Status;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.repository.IncidenciaRepository;
import com.micromax.incidencia.service.IncidenciaService;
import com.micromax.incidencia.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class IncidenciaServiceImpl implements IncidenciaService {

    @Autowired
    private IncidenciaRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public List<Incidencia> getIncidencias() {
        log.info("Buscando todas las incidencias");
        return (ArrayList<Incidencia>) repository.findAllByActiva(true);
    }

    @Override
    public void guardarIncidencia(Incidencia incidencia, String username){
        incidencia.setCreador(usuarioService.getUsuarioByUsername(username));
        incidencia.setCreacion(LocalDateTime.now());
        incidencia.setStatus(Status.NUEVA);

        incidencia = repository.save(incidencia);
        log.info("Usuario %s ha creado una incidencia nueva con id %d", incidencia.getIdIncidencia(), username);
    }

    @Override
    public Incidencia getIncidenciaById(long id) {
        log.info("Buscando incidencia con id %d", id);
        return repository.findByIdIncidenciaAndActiva(id, true).orElse(null);
    }

    @Override
    public boolean borrarIncidencia(Long id) {
        Incidencia i = repository.findById(id).orElse(  null);
        if(i != null) {
            i.setActiva(false);
            log.info("Eliminada incidencia con id %d", id);
            return repository.save(i) != null;
        }
        return false;
    }
}
