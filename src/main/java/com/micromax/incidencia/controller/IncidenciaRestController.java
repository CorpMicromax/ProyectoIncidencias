package com.micromax.incidencia.controller;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.service.ItemListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IncidenciaRestController {

    @Autowired
    private ItemListService itemListService;

    @GetMapping("/rest/incidencia/getCategoriasDos")
    public List<Categoria> categoriasDos(@RequestParam int id) {
        return itemListService.getCategoriasNivelDos((long)id);
    }

    @GetMapping("/rest/incidencia/getCategoriasUno")
    public List<Categoria> categoriasUno() {
        return itemListService.getCategoriasNivelUno();
    }
}
