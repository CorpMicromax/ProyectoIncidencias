package com.micromax.incidencia.service;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.domain.entities.incidencias.TipoIncidencia;
import com.micromax.incidencia.dto.CategoriaDTO;

import java.util.List;


public interface ItemListService {

    // Servicios para la Clase Categoria

    List<Categoria> getCategoriasNivelUno();

    List<Categoria> getCategoriasNivelDos(long idPadre);

    Categoria getCategoria(long id);

    List<Categoria> getAllCategorias();

    List<Categoria> getCategoriaByNivel(int nivel);

    Categoria guardar(CategoriaDTO cat);

    boolean eliminarCategoria(Categoria id);


    // Servicios para la Clase TipoIncidencia

    TipoIncidencia getTipoIncidencia(long id);

    List<TipoIncidencia> getAllTipoIncidencias();

    TipoIncidencia getTipoIncidenciaById(long id);

    TipoIncidencia createTipoIncidencia(TipoIncidencia tipoIncid);

    TipoIncidencia guardar(TipoIncidencia tipoIncidencia);


}
