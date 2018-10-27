package com.micromax.incidencia.repository;

import com.micromax.incidencia.domain.entities.users.Permiso;
import org.springframework.data.repository.CrudRepository;

public interface PrivilegioRepository extends CrudRepository<Permiso, Long> {

    public Permiso findByNombre(String name);
}
