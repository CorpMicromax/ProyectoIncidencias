package com.micromax.incidencia.controller;

import com.micromax.incidencia.domain.Constants;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.service.IncidenciaService;
import com.micromax.incidencia.service.UsuarioService;
import com.micromax.incidencia.viewmodel.DashboardViewmodel;
import com.micromax.incidencia.viewmodel.HomeViewmodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.micromax.incidencia.controller.MasterCrudController.setTemplateToModel;
import static com.micromax.incidencia.domain.Constants.TITLE;

@Controller
public class MainController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private IncidenciaService incidenciaService;

    @GetMapping(value = {"/home","/"})
    public String homeRoute(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nombre = auth.getName();

        Usuario user = usuarioService.getUsuarioByUsername(nombre);
        assert(user != null);
        HomeViewmodel viewmodel = new HomeViewmodel();
        viewmodel.setAuthenticated(auth.isAuthenticated());
        viewmodel.setUsername(user.getUsername());
        viewmodel.setNombre(user.getPrimerNombreYPrimerApellido());
        viewmodel.setRol(user.getRol());
        viewmodel.setAdmin(user.getRol().getNombre().equalsIgnoreCase(Constants.ADMINROLE));
        viewmodel.setTech(user.getRol().getNombre().equalsIgnoreCase(Constants.TECHROLE));
        viewmodel.setClient(user.getRol().getNombre().equalsIgnoreCase(Constants.CLIENTROLE));
        model.addAttribute("homeData", viewmodel);

        if (!model.containsAttribute("location") || !model.containsAttribute("template")){
            DashboardViewmodel dash = incidenciaService.obtenerTodasIncidencias(usuarioService.getUsuarioByUsername(nombre));
            model.addAttribute("data", dash)
                    .addAttribute("location", "")
                    .addAttribute("template","dashboard")
                    .addAttribute(Constants.TITLE, "Home");
        }

        return "home2";
    }

    @GetMapping(value = {"/admin/reportes"})
    public String reportes(Model model){
        HomeViewmodel viewmodel = new HomeViewmodel();
        model = setTemplateToModel(model, "","reportes")
                .addAttribute(Constants.DATA, viewmodel)
                .addAttribute(TITLE,"Reportes");
        return homeRoute(model);
    }

}
