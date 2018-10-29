package com.micromax.incidencia.controller;

import com.micromax.incidencia.domain.Constants;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.service.IncidenciaService;
import com.micromax.incidencia.service.ItemListService;
import com.micromax.incidencia.service.UsuarioService;
import com.micromax.incidencia.viewmodel.IncidenciaViewmodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        viewmodel.setIncidencia(new Incidencia());
        viewmodel.setMessage("");
        viewmodel.setCategorias(itemListService.getCategoriaByNivel(1));

        model = setTemplateToModel(model, INCIDENCIA,"incidenciaC")
                .addAttribute(Constants.DATA, viewmodel)
                .addAttribute(TITLE,"Crear Incidencia");
        return mainController.homeRoute(model);
    }

    @GetMapping("/incidenciaL")
    public String incidenciaL(@RequestParam(value = "id", required = false) Long id, Model model){
        model = setTemplateToModel(model, INCIDENCIA,"incidenciaL")
                .addAttribute("incidencias", incidenciaService.getIncidencias())
                .addAttribute(TITLE,"Ver incidencias");
        if(id != null){
            model.addAttribute("relevant", id);
        }
        return mainController.homeRoute(model);
    }

    /*======================================= POSTS ========================================*/

    @GetMapping("/incidenciaE")
    public String incidenciaE(@RequestParam long id, Model model){

        IncidenciaViewmodel viewmodel = new IncidenciaViewmodel();
        viewmodel.setIncidencia(incidenciaService.getIncidenciaById(id));
        viewmodel.setMessage("");
        viewmodel.setCategorias(itemListService.getCategoriaByNivel(1));

        model = setTemplateToModel(model, INCIDENCIA,"incidenciaE")
                .addAttribute(Constants.DATA, viewmodel)
                .addAttribute(TITLE,"Editar Incidencia");
        return mainController.homeRoute(model);
    }

    /* INCIDENCIA */
    @PostMapping("/incidenciaC")
    public String postIncidenciaC(@RequestParam IncidenciaViewmodel viewmodel, BindingResult errors, Model model){
        incidenciaService.guardarIncidencia(viewmodel.getIncidencia(), SecurityContextHolder.getContext().getAuthentication().getName());
        return "redirect:/incidenciaL?=" + viewmodel.getIncidencia().getIdIncidencia();
    }

    @PostMapping("/incidenciaE")
    public String postIncidenciaE(@RequestParam IncidenciaViewmodel viewmodel, BindingResult errors, Model model){
        viewmodel.getIncidencia().setCreador(usuarioService.getUsuarioByUsername(viewmodel.getIncidencia().getCreador().getUsername()));
        incidenciaService.actualizarIncidencia(viewmodel.getIncidencia());

        return "redirect:/incidenciaL?=" + viewmodel.getIncidencia().getIdIncidencia();
    }
}
