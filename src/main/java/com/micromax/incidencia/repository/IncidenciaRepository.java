package com.micromax.incidencia.repository;

import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import org.springframework.data.repository.CrudRepository;

public interface IncidenciaRepository extends CrudRepository<Incidencia,Long> {
}
