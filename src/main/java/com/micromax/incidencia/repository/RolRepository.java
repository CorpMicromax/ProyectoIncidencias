package com.micromax.incidencia.repository;

import com.micromax.incidencia.domain.entities.users.Rol;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface RolRepository extends CrudRepository<Rol,Long> {

    Rol findByNombreAndActiva(String name, boolean activa);

    Collection<Rol> findAllByActiva(boolean activa);

}
