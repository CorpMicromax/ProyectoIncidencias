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
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
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
        return (ArrayList<Incidencia>) repository.findAllByHabilitado(true);
    }

    @Override
    public void guardarIncidencia(Incidencia incidencia, String username){
        incidencia.setCreador(usuarioService.getUsuarioByUsername(username));
        incidencia.setCreacion(Date.from(LocalDateTime.now().toInstant(ZoneOffset.ofHours(0))));
        incidencia.setStatus(Status.NUEVA);

        incidencia = repository.save(incidencia);
        log.info("Usuario %s ha creado una incidencia nueva con id %d", incidencia.getIdIncidencia(), username);
    }

    @Override
    public void actualizarIncidencia(Incidencia i) {
        i = repository.save(i);
        log.info("Usuario %s ha creado una incidencia nueva con id %d", i.getIdIncidencia());
    }

    @Override
    public Incidencia getIncidenciaById(String id) {
        log.info("Buscando incidencia con id %d", id);
        return repository.findByIdIncidenciaAndHabilitado(id, true).orElse(null);
    }

    @Override
    public boolean borrarIncidencia(String id) {
        Incidencia i = repository.findByIdIncidenciaAndHabilitado(id, true).orElse(  null);
        if(i != null) {
            i.setHabilitado(false);
            log.info("Eliminada incidencia con id %d", id);
            return repository.save(i) != null;
        }
        return false;
    }


}
