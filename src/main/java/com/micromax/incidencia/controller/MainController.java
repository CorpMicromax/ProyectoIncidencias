package com.micromax.incidencia.controller;

import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

        model.addAttribute("nombre", nombre);
        model.addAttribute("authorization", auth.isAuthenticated() );
        model.addAttribute("user_role", user.getRol());
        model.addAttribute("permisos", user.getRol().getPermisos());
        return "home";
    }

}
