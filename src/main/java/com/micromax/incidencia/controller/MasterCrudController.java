package com.micromax.incidencia.controller;

import com.micromax.incidencia.dto.CategoriaDTO;
import com.micromax.incidencia.dto.IncidenciaDTO;
import com.micromax.incidencia.service.IncidenciaService;
import com.micromax.incidencia.service.ItemListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MasterCrudController {

    @Autowired
    private IncidenciaService incidenciaService;

    @Autowired
    private ItemListService itemListService;

    /*===================== GETS ================================*/
    @GetMapping("/incidenciaC")
    public String formularioCrear(Model model){
        model.addAttribute("categoriasUno", itemListService.getCategoriasNivelUno());
        model.addAttribute("incidencia", new IncidenciaDTO());
        model.addAttribute("message","Incidencia Creada");
        return "incidencia/crear";
    }

    @GetMapping("/incidenciaL")
    public String listar(Model model){
        model.addAttribute("incidencias", incidenciaService.getIncidencias());
        return "incidencia/listar";
    }

    @GetMapping("/categoriaC")
    public String crearCategoria(Model model){
        model.addAttribute("categoria", new CategoriaDTO());
        model.addAttribute("categorias", itemListService.getCategoriaByNivel(1));
        return "master/crearCat";
    }

    @PostMapping("/incidenciaC")
    public String crearIncidencia(@ModelAttribute IncidenciaDTO incidencia, BindingResult errors, Model model){
        incidenciaService.createIncidencia(incidencia, SecurityContextHolder.getContext().getAuthentication().getName());
        return formularioCrear(model);
    }

    @PostMapping("/categoriaC")
    public String crearCategoria(@ModelAttribute CategoriaDTO cat, BindingResult errors, Model model){
        itemListService.guardar(cat);
        return crearCategoria(model);
    }
}
