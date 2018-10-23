package com.micromax.incidencia.repository;

import com.micromax.incidencia.domain.entities.users.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario,Long> {

    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByEmail(String email);

    Collection<Usuario> findAllByActiva(boolean b);

    Usuario findByIdUsuarioAndActiva(long id, boolean a);

    Usuario findByUsernameAndActiva(String username, boolean b);
}
