package com.micromax.incidencia.service.impl;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.domain.entities.incidencias.TipoIncidencia;
import com.micromax.incidencia.dto.CategoriaDTO;
import com.micromax.incidencia.repository.CategoriaRepository;
import com.micromax.incidencia.repository.TipoIncidenciaRepository;
import com.micromax.incidencia.service.ItemListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ItemListServiceImpl implements ItemListService {


    @Autowired
    private CategoriaRepository categoriaRepository;
    private TipoIncidenciaRepository tipoIncidenciaRepository;

    // Implementación para los tipos de Categoría

    @Override
    public List<Categoria> getCategoriasNivelUno() {
        return (ArrayList<Categoria>) categoriaRepository.findByNivelAndActiva(1, true);
    }

    @Override
    public List<Categoria> getCategoriasNivelDos(long idPadre) {
        Categoria padre = getCategoria(idPadre);
        if(padre.getNivel()!=1)return new ArrayList<>();
        return (ArrayList<Categoria>) categoriaRepository.findByPadreAndActiva(getCategoria(idPadre), true);
    }

    @Override
    public Categoria getCategoria(long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Categoria> getAllCategorias() {
        return (List<Categoria>) categoriaRepository.findByActiva(true);
    }

    @Override
    public List<Categoria> getCategoriaByNivel(int nivel) {
        return (List<Categoria>)categoriaRepository.findByNivelAndActiva(nivel, true);
    }

    @Override
    public Categoria guardar(CategoriaDTO categoriaDTO) {
        return categoriaRepository.save(new Categoria(categoriaDTO));
    }


    @Override
    public boolean eliminarCategoria(Categoria cat) {
        cat.setActiva(false);
        categoriaRepository.save(cat);
        return true;
    }

    // Implementación para los Tipos de Incidencia

    @Override
    public TipoIncidencia getTipoIncidencia(long id) {
      //  return tipoIncidenciaRepository.findById(id).orElse(null);
        log.info("Buscando Tipo de Incidencia por id %d", id);
        Optional<TipoIncidencia> i = tipoIncidenciaRepository.findById(id);
        return i.orElse(null);
    }

    @Override
    public List<TipoIncidencia> getAllTipoIncidencias() {
        log.info("Buscando todos los tipos de incidencias");
        return (ArrayList<TipoIncidencia>) tipoIncidenciaRepository.findAll();
    }


    @Override
    public TipoIncidencia guardar(TipoIncidencia tipoIncidencia) {
        return tipoIncidenciaRepository.save(tipoIncidencia);
    }

    @Override
    public TipoIncidencia getTipoIncidenciaById(long id) {
        return tipoIncidenciaRepository.findById(id).orElse(null);
    }

    @Override
    public TipoIncidencia createTipoIncidencia(TipoIncidencia tipoIncid) {
        return tipoIncidenciaRepository.save(tipoIncid);
    }
}
