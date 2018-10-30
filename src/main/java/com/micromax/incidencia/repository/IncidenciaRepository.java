package com.micromax.incidencia.repository;

import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Tecnico;
import com.micromax.incidencia.domain.entities.users.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IncidenciaRepository extends CrudRepository<Incidencia,Long> {

    Collection<Incidencia> findAllByHabilitadoIsTrue();

    Optional<Incidencia> findByIdIncidenciaAndHabilitadoIsTrue(String id);

    List<Incidencia> findAllByCreadorAndHabilitadoIsTrue(Usuario creador);

    List<Incidencia> findAllByAsignadosContains(Tecnico tecnico);
}
