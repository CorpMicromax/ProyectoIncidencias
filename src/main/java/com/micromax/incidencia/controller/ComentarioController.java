package com.micromax.incidencia.controller;

import com.micromax.incidencia.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class ComentarioController {

    @Autowired
    private ComentarioService ComentarioService;

    @PostMapping("/ComentarioC")
    public String crearComentario(@ModelAttribute BindingResult errors, Model model){
        //ComentarioService.createComentario(comentario, SecurityContextHolder.getContext().getAuthentication().getComentario());
        return formularioCrearComentario(model);
    }

    @GetMapping("/ComentarioC")
    public String formularioCrearComentario(Model model){
        //model.addAttribute("Comentario", contenido);
        //model.addAttribute("incidencia", new IncidenciaDTO());
        model.addAttribute("message","Incidencia Creada");
        return "incidencia/crear";
    }

    @GetMapping("/incidenciaL")
    public String listar(Model model){
        //model.addAttribute("incidencias", incidenciaService.getIncidencias());
        return "listado";
    }

}
