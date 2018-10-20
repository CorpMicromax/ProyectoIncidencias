package com.micromax.incidencia.service.Impl;

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
        return (ArrayList<Categoria>) categoriaRepository.findByNivel(1);
    }

    @Override
    public List<Categoria> getCategoriasNivelDos(long idPadre) {
        return (ArrayList<Categoria>) categoriaRepository.findByPadre(getCategoria(idPadre));
    }

    @Override
    public Categoria getCategoria(long id) {
        return categoriaRepository.findById(id).orElse(null);
    }


}
