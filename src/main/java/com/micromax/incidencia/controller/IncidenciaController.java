package com.micromax.incidencia.controller;

import com.micromax.incidencia.dto.IncidenciaDTO;
import com.micromax.incidencia.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/incidenciaC")
    public String crearIncidencia(@ModelAttribute IncidenciaDTO incidencia, BindingResult errors, Model model){
        incidenciaService.createIncidencia(incidencia);
        model.addAttribute(new IncidenciaDTO());
        model.addAttribute("success", "success");
        return "crearIncidencia";
    }

    @GetMapping("/incidenciaC")
    public String formularioCrear(Model model){
        model.addAttribute("incidencia", new IncidenciaDTO());
        return "incidencia/crear";
    }

    @GetMapping("/incidenciaL")
    public String listar(Model model){
        model.addAttribute("incidencias", incidenciaService.getIncidencias());
        return "listado";
    }

}
