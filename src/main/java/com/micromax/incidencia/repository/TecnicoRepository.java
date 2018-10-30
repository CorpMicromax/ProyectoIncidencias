package com.micromax.incidencia.repository;

import com.micromax.incidencia.domain.entities.users.Tecnico;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface TecnicoRepository extends CrudRepository<Tecnico,Long> {


    Collection<Tecnico> findAllByHabilitadoIsTrue();

    Tecnico findTecnicoByUsernameAndHabilitadoIsTrue(String username);

    Tecnico findTecnicoByIdUsuarioAndHabilitadoIsTrue(long id);
}

