package com.micromax.incidencia.controller;

import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.dto.CategoriaDTO;
import com.micromax.incidencia.service.IncidenciaService;
import com.micromax.incidencia.service.ItemListService;
import com.micromax.incidencia.viewmodel.IncidenciaViewmodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MasterCrudController {

    @Autowired
    private IncidenciaService incidenciaService;


    @Autowired
    private MainController mainController;

    @Autowired
    private ItemListService itemListService;

    /*======================================= GETS ========================================*/


    /*-------------- INCIDENCIA -------------*/
    @GetMapping("/incidenciaC")
    public String incidenciaC(Model model){
        IncidenciaViewmodel viewmodel = new IncidenciaViewmodel();
        viewmodel.setIncidencia(new Incidencia());
        viewmodel.setMessage("");
        viewmodel.setCategorias(itemListService.getCategoriasNivelUno());

        model = setTemplateToModel(model,"/incidencia/","incidenciaC")
                .addAttribute("data", viewmodel)
                .addAttribute("title","Crear Incidencia");
        return mainController.homeRoute(model);
    }

    @GetMapping("/incidenciaL")
    public String incidenciaL(@RequestParam(value = "id", required = false) Long id, Model model){
        model = setTemplateToModel(model,"/incidencia/","incidenciaL")
                .addAttribute("incidencias", incidenciaService.getIncidencias())
                .addAttribute("title","Ver incidencias");
        if(id != null){
            model.addAttribute("relevant", id);
        }
        return mainController.homeRoute(model);
    }

    @GetMapping("/incidenciaE")
    public String incidenciaE(@RequestParam long id, Model model){

        IncidenciaViewmodel viewmodel = new IncidenciaViewmodel();
        viewmodel.setIncidencia(incidenciaService.getIncidenciaById(id));
        viewmodel.setMessage("");
        viewmodel.setCategorias(itemListService.getCategoriasNivelUno());

        model = setTemplateToModel(model,"/incidencia/","incidenciaE")
                .addAttribute("data", viewmodel)
                .addAttribute("title","Editar Incidencia");
        return mainController.homeRoute(model);
    }

    /*-------------- CATEGORIA -------------*/
    @GetMapping("/categoriaC")
    public String crearCategoria(Model model){
        model.addAttribute("categoria", new CategoriaDTO());
        model.addAttribute("categorias", itemListService.getCategoriaByNivel(1));
        return "master/crearCat";
    }


    /*======================================= POSTS ========================================*/
    @PostMapping("/incidenciaC")
    public String crearIncidencia(@ModelAttribute IncidenciaViewmodel viewmodel, BindingResult errors, Model model){
        incidenciaService.createIncidencia(viewmodel.getIncidencia(), SecurityContextHolder.getContext().getAuthentication().getName());
        return "redirect:/incidenciaL?=" + viewmodel.getIncidencia().getIdIncidencia();
    }

    @PostMapping("/categoriaC")
    public String crearCategoria(@ModelAttribute CategoriaDTO cat, BindingResult errors, Model model){
        itemListService.guardar(cat);
        return crearCategoria(model);
    }

    private Model setTemplateToModel(Model model, String location, String template){
        return model.addAttribute("location", location).addAttribute("template", template);
    }
}
