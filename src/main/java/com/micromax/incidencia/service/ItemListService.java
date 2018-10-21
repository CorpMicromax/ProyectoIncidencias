package com.micromax.incidencia.service;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;

import java.util.List;


public interface ItemListService {

    List<Categoria> getCategoriasNivelUno();

    List<Categoria> getCategoriasNivelDos(long idPadre);

    Categoria getCategoria(long id);

    List<Categoria> getAllCategorias();

    List<Categoria> getCategoriaByNivel(int nivel);

    Categoria guardar(Categoria cat);

    boolean eliminarCategoria(Categoria id);
}
