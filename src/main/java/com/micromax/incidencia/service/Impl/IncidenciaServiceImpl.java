package com.micromax.incidencia.service.Impl;

import com.micromax.incidencia.domain.Status;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.dto.IncidenciaDTO;
import com.micromax.incidencia.repository.IncidenciaRepository;
import com.micromax.incidencia.service.IncidenciaService;
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
    public void createIncidencia(IncidenciaDTO i){
        Incidencia incidencia = new Incidencia(i);
        incidencia.setStatus(Status.NUEVA);

        repository.save(incidencia);
    }
}
