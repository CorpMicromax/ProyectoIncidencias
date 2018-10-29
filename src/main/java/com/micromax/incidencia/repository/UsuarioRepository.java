package com.micromax.incidencia.repository;

import com.micromax.incidencia.domain.entities.users.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario,Long> {

    Usuario findUsuarioByUsernameAndHabilitado(String username, boolean h);

    Optional<Usuario> findByEmailAndHabilitado(String email, boolean h);

    Collection<Usuario> findAllByHabilitado(boolean b);

    Usuario findByIdUsuarioAndHabilitado(long id, boolean a);

    boolean existsByUsernameAndHabilitado(String username, boolean habilitado);
    
}
