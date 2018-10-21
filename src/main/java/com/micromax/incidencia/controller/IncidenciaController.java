package com.micromax.incidencia.controller;

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
public class IncidenciaController {

    @Autowired
    private IncidenciaService incidenciaService;

    @Autowired
    private ItemListService itemListService;

    @PostMapping("/incidenciaC")
    public String crearIncidencia(@ModelAttribute IncidenciaDTO incidencia, BindingResult errors, Model model){
        incidenciaService.createIncidencia(incidencia, SecurityContextHolder.getContext().getAuthentication().getName());
        return formularioCrear(model);
    }

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
        return "listado";
    }

}
