package com.micromax.incidencia.controller;

import com.micromax.incidencia.domain.Constants;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Cliente;
import com.micromax.incidencia.domain.entities.users.Tecnico;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.dto.IncidenciaDTO;
import com.micromax.incidencia.service.IncidenciaService;
import com.micromax.incidencia.service.ItemListService;
import com.micromax.incidencia.service.UsuarioService;
import com.micromax.incidencia.viewmodel.IncidenciaViewmodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

import static com.micromax.incidencia.controller.MasterCrudController.setTemplateToModel;
import static com.micromax.incidencia.domain.Constants.INCIDENCIA;
import static com.micromax.incidencia.domain.Constants.TITLE;

@Controller
public class IncidenciaController {



    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private IncidenciaService incidenciaService;

    @Autowired
    private MainController mainController;

    @Autowired
    private ItemListService itemListService;

    /*-------------- INCIDENCIA -------------*/
    @GetMapping("/incidenciaC")
    public String incidenciaC(Model model){
        IncidenciaViewmodel viewmodel = new IncidenciaViewmodel();
        viewmodel.setIncidenciaDTO(new IncidenciaDTO());
        viewmodel.setMessage("");
        viewmodel.setCategorias(itemListService.getCategoriaByNivel(1));

        model = setTemplateToModel(model, INCIDENCIA,"incidenciaC")
                .addAttribute(Constants.DATA, viewmodel)
                .addAttribute(TITLE,"Crear Incidencia");
        return mainController.homeRoute(model);
    }

    @GetMapping("/incidenciaE")
    public String incidenciaE(@RequestParam String id, Model model){

        IncidenciaViewmodel viewmodel = new IncidenciaViewmodel();
        viewmodel.setIncidenciaDTO(new IncidenciaDTO(incidenciaService.getIncidenciaById(id)));
        viewmodel.setMessage("");
        viewmodel.setCategorias(itemListService.getCategoriaByNivel(1));

        model = setTemplateToModel(model, INCIDENCIA,"incidenciaE")
                .addAttribute(Constants.DATA, viewmodel)
                .addAttribute(TITLE,"Editar Incidencia");
        return mainController.homeRoute(model);
    }

    @GetMapping("/incidenciaL")
    public String incidenciaL(@RequestParam(value = "id", required = false) Long id, Model model){
        List<Incidencia> incidencias;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario u = usuarioService.getUsuarioByUsername(auth.getName());
        if(obtenerRol().equalsIgnoreCase("ROLE_ADMIN")){
            incidencias = incidenciaService.getIncidencias();

        }else
        if( u instanceof Tecnico){
            incidencias = u.getIncidencias();
            incidencias.addAll(((Tecnico) u).getAsignaciones());
        }else
        if(obtenerRol().equalsIgnoreCase("ROLE_CLIENT") || u instanceof Cliente){
            incidencias = incidenciaService.obtenerIncidenciasPorCreador(u);
        }else{
            incidencias = Collections.EMPTY_LIST;
        }

        model = setTemplateToModel(model, INCIDENCIA,"incidenciaL")
                .addAttribute("incidencias", incidencias)
                .addAttribute(TITLE,"Ver incidencias");
        if(id != null){
            model.addAttribute("relevant", id);
        }
        return mainController.homeRoute(model);
    }

    /*======================================= POSTS ========================================*/

    /* INCIDENCIA */
    @PostMapping("/incidenciaC")
    public String postIncidenciaC(@ModelAttribute IncidenciaViewmodel viewmodel, BindingResult errors, Model model){
        incidenciaService.guardarIncidencia(viewmodel.getIncidenciaDTO(), SecurityContextHolder.getContext().getAuthentication().getName());
        return "redirect:/incidenciaL";
    }

    @PostMapping("/incidenciaE")
    public String postIncidenciaE(@ModelAttribute IncidenciaViewmodel viewmodel, BindingResult errors, Model model){
        incidenciaService.actualizarIncidencia(viewmodel.getIncidenciaDTO());
        return "redirect:/incidenciaL";
    }

    private String obtenerRol(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return usuarioService.getUsuarioByUsername(auth.getName()).getRol().getNombre();
    }
}
