package com.micromax.incidencia.service;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.dto.CategoriaDTO;

import java.util.List;


public interface ItemListService {

    List<Categoria> getCategoriasNivelUno();

    List<Categoria> getCategoriasNivelDos(long idPadre);

    Categoria getCategoria(long id);

    List<Categoria> getAllCategorias();

    List<Categoria> getCategoriaByNivel(int nivel);

    Categoria guardar(CategoriaDTO cat);

    boolean eliminarCategoria(Categoria id);
}
