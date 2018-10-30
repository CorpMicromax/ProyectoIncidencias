package com.micromax.incidencia.repository;

import com.micromax.incidencia.domain.entities.users.Tecnico;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface TecnicoRepository extends CrudRepository<Tecnico,Long> {


    Collection<Tecnico> findAllByHabilitado(boolean h);

    Tecnico findTecnicoByIdUsuarioAndHabilitado(Long id, boolean h);

    Tecnico findTecnicoByUsernameAndHabilitado(String username, boolean h);
}

