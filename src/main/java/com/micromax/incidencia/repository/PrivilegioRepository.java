package com.micromax.incidencia.repository;

import com.micromax.incidencia.domain.entities.users.Privilegio;
import org.springframework.data.repository.CrudRepository;

public interface PrivilegioRepository extends CrudRepository<Privilegio, Long> {

    public Privilegio findByNombre(String name);
}
