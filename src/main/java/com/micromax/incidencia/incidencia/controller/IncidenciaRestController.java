package com.micromax.incidencia.incidencia.controller;

import com.micromax.incidencia.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.incidencia.service.ItemListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IncidenciaRestController {

    @Autowired
    private ItemListService itemListService;

    @GetMapping("/rest/incidencia/getCategoriasDos")
    @RequestMapping("/greeting")
    public List<Categoria> categoriasDos(@RequestParam(value="id", defaultValue="0") long idNivelUno) {
        return itemListService.getCategoriasNivelDos(idNivelUno);
    }
}
