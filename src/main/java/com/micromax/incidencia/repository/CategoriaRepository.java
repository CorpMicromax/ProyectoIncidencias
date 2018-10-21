package com.micromax.incidencia.repository;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

    Iterable<Categoria> findByPadreAndActiva(Categoria padre, boolean activa);

    Iterable<Categoria> findByNivelAndActiva(int nivel, boolean activa);

    Categoria findByNombreAndActiva(String name, boolean activa);

    Iterable<Categoria> findByActiva(boolean activa);

    Optional<Categoria> findByIdAndActiva(long id, boolean activa);
}
