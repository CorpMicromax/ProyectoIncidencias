package com.micromax.incidencia.repository;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

    Iterable<Categoria> findByPadre(Categoria padre);

    Iterable<Categoria> findByNivel(int nivel);
}
