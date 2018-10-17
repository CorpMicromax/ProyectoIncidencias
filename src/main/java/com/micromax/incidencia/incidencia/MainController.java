package com.micromax.incidencia.incidencia;

import com.micromax.incidencia.incidencia.domain.Incidencia;
import com.micromax.incidencia.incidencia.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class MainController {

    @Autowired
    private IncidenciaService service;

    @GetMapping("/home")
    public String homeRoute(){
    return "home";
    }

    @GetMapping("/")
    public String rootRoute(){
        return "home";
    }

    @PostMapping("/crearIncidencia")
    public String crearIncidencia(@ModelAttribute Incidencia incidencia, BindingResult errors, Model model){
        service.createIncidencia(incidencia);
        model.addAttribute(new Incidencia());
        model.addAttribute("success", "success");
        return "crearIncidencia";
    }

    @GetMapping("/crear")
    public String crearIncidencia2(Model model){

        model.addAttribute(new Incidencia());
        return "crearIncidencia";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("nombre", "Javier");
        model.addAttribute("serverTime", LocalDateTime.now());
        model.addAttribute("incidencias", service.getIncidencias());
        return "listado";
    }
}
