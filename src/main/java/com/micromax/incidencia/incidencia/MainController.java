package com.micromax.incidencia.incidencia;

import com.micromax.incidencia.incidencia.domain.Incidencia;
import com.micromax.incidencia.incidencia.domain.Usuario;
import com.micromax.incidencia.incidencia.dto.UserLoginDTO;
import com.micromax.incidencia.incidencia.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    private IncidenciaService service;

    @GetMapping("/home")
    public String homeRoute(){
    return "home";
    }

    @GetMapping("/")
    public String rootRoute(Model model){
        model.addAttribute("usuario", new UserLoginDTO());
        return "login";
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

    @PostMapping("/ingresar")
    public String ingresar(@ModelAttribute Usuario dto, BindingResult errors, Model model){
        return "home";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("incidencias", service.getIncidencias());
        return "listado";
    }
}
