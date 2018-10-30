package com.micromax.incidencia.repository;

import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface IncidenciaRepository extends CrudRepository<Incidencia,Long> {

    Collection<Incidencia> findAllByHabilitado(boolean activa);

    Optional<Incidencia> findByIdIncidenciaAndHabilitado(String id, boolean activa);
}
