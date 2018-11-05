package com.micromax.incidencia.repository;

import com.micromax.incidencia.domain.Status;
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

    List<Incidencia> findAllByStatusAndCreadorAndHabilitadoIsTrue(Status status, Usuario creador);

    List<Incidencia> findAllByStatusIsNotAndCreadorAndHabilitadoIsTrue(Status status, Usuario creador);

    List<Incidencia> findAllByStatusAndHabilitadoIsTrue(Status status);

    int countAllByStatusAndHabilitadoIsTrue(Status status);

    int countAllByStatusAndCreadorAndHabilitadoIsTrue(Status status, Usuario user);

    int countAllByHabilitadoIsTrue();
}
