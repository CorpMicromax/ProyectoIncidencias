package com.micromax.incidencia.incidencia.repository;

import com.micromax.incidencia.incidencia.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario,Long> {
}
