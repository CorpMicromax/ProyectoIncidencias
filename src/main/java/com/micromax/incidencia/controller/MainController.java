package com.micromax.incidencia.controller;

import com.micromax.incidencia.domain.entities.users.Permiso;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.service.UsuarioService;
import com.micromax.incidencia.viewmodel.HomeViewmodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = {"/home","/"})
    public String homeRoute(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nombre = auth.getName();

        Usuario user = usuarioService.getUsuarioByUsername(nombre);
        assert(user != null);
        HomeViewmodel viewmodel = new HomeViewmodel();
        viewmodel.setAuthenticated( auth.isAuthenticated());
        viewmodel.setNombre(user.getPrimerNombreYPrimerApellido());
        viewmodel.setRol(user.getRol());
        viewmodel.setPermisos((List<Permiso>)user.getRol().getPermisos());

        model.addAttribute("homeData", viewmodel);
        return "home";
    }

//    @GetMapping(value="/error")
//    public String error(){
//        return "error";
//    }

}
