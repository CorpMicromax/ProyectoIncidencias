package com.micromax.incidencia.repository;

import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface IncidenciaRepository extends CrudRepository<Incidencia,Long> {

    Collection<Incidencia> findAllByActiva(boolean activa);

    Optional<Incidencia> findByIdIncidenciaAndActiva(long id, boolean activa);
}
