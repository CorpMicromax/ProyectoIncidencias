package com.micromax.incidencia.incidencia.service.Impl;

import com.micromax.incidencia.incidencia.Status;
import com.micromax.incidencia.incidencia.domain.Incidencia;
import com.micromax.incidencia.incidencia.repository.IncidenciaRepository;
import com.micromax.incidencia.incidencia.service.IncidenciaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class IncidenciaServiceImpl implements IncidenciaService {

    @Autowired
    private IncidenciaRepository repository;

    @Override
    public List<Incidencia> getIncidencias() {
        log.info("Buscando todas las incidencias");
        return (ArrayList<Incidencia>) repository.findAll();
    }

    @Override
    public Incidencia getIncidencia(long id) {
        log.info("Buscando Incidencia por id %d", id);
        Optional<Incidencia> i = repository.findById(id);
        return i.orElse(null);
    }

    @Override
    public void createIncidencia(Incidencia i){
        i.setStatus(Status.NUEVA);
        repository.save(i);
    }
}
