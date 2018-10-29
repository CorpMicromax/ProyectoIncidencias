package com.micromax.incidencia.repository;

import com.micromax.incidencia.domain.entities.users.Rol;
import org.springframework.data.repository.CrudRepository;

public interface RolRepository extends CrudRepository<Rol,Long> {

    Rol findByNombre(String name);

    Rol findByIdRol(int id);
}
