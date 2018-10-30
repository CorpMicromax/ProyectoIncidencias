package com.micromax.incidencia.repository;

import com.micromax.incidencia.domain.entities.users.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteRepository extends CrudRepository<Cliente,Long> {

    List<Cliente> findAllByHabilitado(boolean h);

    Cliente findClienteByIdUsuarioAndHabilitado(Long id, boolean h);

    Cliente findClienteByUsernameAndHabilitado(String username, boolean h);
}
