package com.micromax.incidencia.incidencia.repository;

import com.micromax.incidencia.incidencia.domain.entities.incidencias.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

    Iterable<Categoria> findByPadre(long idPadre);

    Iterable<Categoria> findByNivel(int nivel);

}
