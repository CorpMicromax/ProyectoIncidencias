package com.micromax.incidencia.incidencia.repository;

import com.micromax.incidencia.incidencia.domain.entities.users.Rol;
import org.springframework.data.repository.CrudRepository;

public interface RolRepository extends CrudRepository<Rol,Long> {

    Rol findByNombre(String name);
}
