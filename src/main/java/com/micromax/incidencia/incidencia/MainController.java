package com.micromax.incidencia.incidencia;

import com.micromax.incidencia.incidencia.Services.IncidenciaService;
import com.micromax.incidencia.incidencia.dao.Incidencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/createIncidencia")
    public String crearIncidencia(@RequestParam String titulo, @RequestParam int status){

        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo(titulo);
        incidencia.setStatus(Status.values()[status]);
        service.createIncidencia(incidencia);
        return "home";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("nombre", "Javier");
        model.addAttribute("serverTime", LocalDateTime.now());
        model.addAttribute("incidencias", service.getIncidencias());
        return "listado";
    }
}
