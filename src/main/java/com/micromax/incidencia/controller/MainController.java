package com.micromax.incidencia.controller;

import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.dto.UserLoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {



    @GetMapping("/home")
    public String homeRoute(){
    return "home";
    }

    @GetMapping("/")
    public String rootRoute(Model model){
        model.addAttribute("usuario", new UserLoginDTO());
        return "login";
    }

    @PostMapping("/ingresar")
    public String ingresar(@ModelAttribute Usuario dto, BindingResult errors, Model model){
        return "home";
    }


}
