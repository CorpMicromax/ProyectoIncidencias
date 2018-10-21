package com.micromax.incidencia.service.impl;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.repository.CategoriaRepository;
import com.micromax.incidencia.service.ItemListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemListServiceImpl implements ItemListService {

    @Autowired
    private CategoriaRepository categoriaRepository;

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
    public Categoria guardar(Categoria cat) {
        return categoriaRepository.save(cat);
    }

    @Override
    public boolean eliminarCategoria(Categoria cat) {
        cat.setActiva(false);
        categoriaRepository.save(cat);
        return true;
    }
}
