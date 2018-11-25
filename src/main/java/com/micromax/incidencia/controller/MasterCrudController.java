package com.micromax.incidencia.controller;

import com.micromax.incidencia.domain.Constants;
import com.micromax.incidencia.domain.entities.incidencias.TipoIncidencia;
import com.micromax.incidencia.dto.CategoriaDTO;
import com.micromax.incidencia.service.ItemListService;
import com.micromax.incidencia.viewmodel.CategoriaViewmodel;
import com.micromax.incidencia.viewmodel.TipoIncidenciaViewmodel;
import org.springframework.beans.factory.annotation.Autowired;
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
    private MainController mainController;

    @Autowired
    private ItemListService itemListService;

    /*======================================= GETS ========================================*/

    /*-------------- CATEGORIA -------------*/
    @GetMapping("/admin/categoriaC")
    public String crearCategoria(Model model){
        CategoriaViewmodel viewmodel = new CategoriaViewmodel();
        viewmodel.setCategoriaDTO(new CategoriaDTO());
        viewmodel.setCategorias(itemListService.getCategoriaByNivel(1));
        viewmodel.setMessage("");

        model = setTemplateToModel(model, "categoria", "categoriaC")
                .addAttribute(Constants.DATA, viewmodel)
                .addAttribute(Constants.TITLE, "Crear Categoria");
        return mainController.homeRoute(model);
    }
    /*------------- TIPO INCIDENCIA ---------*/
    @GetMapping("/admin/tipoIncidenciaC")
    public String tipoIncidenciaC(Model model){
        TipoIncidenciaViewmodel viewmodel = new TipoIncidenciaViewmodel();
        viewmodel.setTipoIncidencia(new TipoIncidencia());
        viewmodel.setMessage("");

        model = setTemplateToModel(model,"incidencia","tipoIncidenciaC")
                .addAttribute(Constants.DATA, viewmodel)
                .addAttribute(Constants.TITLE,"Crear Tipo de Incidencia");
        return mainController.homeRoute(model);
    }

    @GetMapping("/admin/tipoIncidenciaL")
    public String tipoIncidenciaL(@RequestParam(value = "id", required = false) Long id, Model model){
        model = setTemplateToModel(model,"incidencia","tipoIncidenciaL")
                .addAttribute("TipoIncidencias", itemListService.getAllTipoIncidencias())
                .addAttribute(Constants.TITLE,"Ver tipo de incidencias");
        if(id != null){
            model.addAttribute("relevant", id);
        }
        return mainController.homeRoute(model);
    }

    @GetMapping("/admin/TipIncidenciaE")
    public String tipoIncidenciaE(@RequestParam long id, Model model){

        TipoIncidenciaViewmodel viewmodel = new TipoIncidenciaViewmodel();
        viewmodel.setTipoIncidencia(itemListService.getTipoIncidenciaById(id));
        viewmodel.setMessage("");

        model = setTemplateToModel(model,"incidencia","tipoIncidenciaE")
                .addAttribute(Constants.DATA, viewmodel)
                .addAttribute(Constants.TITLE,"Editar Tipo de Incidencia");
        return mainController.homeRoute(model);
    }

    /*======================================= POSTS ========================================*/

    /*CATEGORIA*/
    @PostMapping("/admin/categoriaC")
    public String postCategoriaC(@ModelAttribute CategoriaViewmodel viewmodel, BindingResult errors, Model model){
        itemListService.guardar(viewmodel.getCategoriaDTO());
        return crearCategoria(model);
    }

    @PostMapping("/admin/tipoIncidenciaC")
    public String crearTipoIncidencia(@ModelAttribute TipoIncidenciaViewmodel viewmodel, BindingResult errors, Model model){
        itemListService.createTipoIncidencia(viewmodel.getTipoIncidencia());
        return "redirect:/home";
    }

    /*TIPO INCIDENCIA*/
    @PostMapping("/admin/tipoIncidenciaE")
    public String crearTipoIncidencia(@ModelAttribute TipoIncidencia tipoIncid, BindingResult errors, Model model){
        itemListService.guardar(tipoIncid);
        return "redirect:/home";
    }

    public static Model setTemplateToModel(Model model, String location, String template) {
        return model.addAttribute("location", location + "/").addAttribute("template", template);
    }
}
