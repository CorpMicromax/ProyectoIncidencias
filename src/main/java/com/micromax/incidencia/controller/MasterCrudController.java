package com.micromax.incidencia.controller;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.incidencias.TipoIncidencia;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.dto.CategoriaDTO;
import com.micromax.incidencia.service.IncidenciaService;
import com.micromax.incidencia.service.ItemListService;
import com.micromax.incidencia.service.UsuarioService;
import com.micromax.incidencia.viewmodel.CategoriaViewmodel;
import com.micromax.incidencia.viewmodel.IncidenciaViewmodel;
import com.micromax.incidencia.viewmodel.TipoIncidenciaViewmodel;
import com.micromax.incidencia.viewmodel.UsuarioViewmodel;
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

    @Autowired
    private UsuarioService usuarioService;

    /*======================================= GETS ========================================*/


    /*-------------- INCIDENCIA -------------*/
    @GetMapping("/incidenciaC")
    public String incidenciaC(Model model){
        IncidenciaViewmodel viewmodel = new IncidenciaViewmodel();
        viewmodel.setIncidencia(new Incidencia());
        viewmodel.setMessage("");
        viewmodel.setCategorias(itemListService.getCategoriasNivelUno());

        model = setTemplateToModel(model,"incidencia","incidenciaC")
                .addAttribute("data", viewmodel)
                .addAttribute("title","Crear Incidencia");
        return mainController.homeRoute(model);
    }

    @GetMapping("/incidenciaL")
    public String incidenciaL(@RequestParam(value = "id", required = false) Long id, Model model){
        model = setTemplateToModel(model,"incidencia","incidenciaL")
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

        model = setTemplateToModel(model,"incidencia","incidenciaE")
                .addAttribute("data", viewmodel)
                .addAttribute("title","Editar Incidencia");
        return mainController.homeRoute(model);
    }

    /*-------------- CATEGORIA -------------*/
    @GetMapping("/categoriaC")
    public String crearCategoria(Model model){
        CategoriaViewmodel viewmodel = new CategoriaViewmodel();
        viewmodel.setCategoria(new Categoria());
        viewmodel.setCategorias(itemListService.getCategoriaByNivel(1));
        viewmodel.setMessage("");

        model = setTemplateToModel(model, "categoria", "categoriaC")
                .addAttribute("data", viewmodel)
                .addAttribute("title", "Crear Categoria");
        return mainController.homeRoute(model);
    }
    /*------------- TIPO INCIDENCIA ---------*/
    @GetMapping("/tipoIncidenciaC")
    public String tipoIncidenciaC(Model model){
        TipoIncidenciaViewmodel viewmodel = new TipoIncidenciaViewmodel();
        viewmodel.setTipoIncidencia(new TipoIncidencia());
        viewmodel.setMessage("");

        model = setTemplateToModel(model,"incidencia","tipoIncidenciaC")
                .addAttribute("data", viewmodel)
                .addAttribute("title","Crear Tipo de Incidencia");
        return mainController.homeRoute(model);
    }

    @GetMapping("/tipoIncidenciaL")
    public String tipoIncidenciaL(@RequestParam(value = "id", required = false) Long id, Model model){
        model = setTemplateToModel(model,"incidencia","tipoIncidenciaL")
                .addAttribute("TipoIncidencias", itemListService.getAllTipoIncidencias())
                .addAttribute("title","Ver tipo de incidencias");
        if(id != null){
            model.addAttribute("relevant", id);
        }
        return mainController.homeRoute(model);
    }

    /*-------------- USUARIO -------------*/
    @GetMapping("/usuarioC")
    public String usuarioC(Model model){
        UsuarioViewmodel viewmodel = new UsuarioViewmodel();
        viewmodel.setUsuario(new Usuario());
        viewmodel.setRoles(usuarioService.getRoles());
        model = setTemplateToModel(model,"usuario","usuarioC");
        model.addAttribute("data", viewmodel);
        model.addAttribute("title", "Crear Usuario");
        return mainController.homeRoute(model);
    }

    @GetMapping("/usuarioE")
    public String usuarioE(@RequestParam long id, Model model){

        UsuarioViewmodel viewmodel = new UsuarioViewmodel();
        viewmodel.setUsuario(usuarioService.getUsuarioById(id));
        viewmodel.setRoles(usuarioService.getRoles());
        viewmodel.setMessage("");

        model = setTemplateToModel(model,"usuario","usuarioE")
                .addAttribute("data", viewmodel)
                .addAttribute("title","Editar Usuario");
        return mainController.homeRoute(model);
    }

    @GetMapping("/usuarioL")
    public String usuarioL(Model model){
        model = setTemplateToModel(model,"usuario","usuarioL")
                .addAttribute("usuarios", usuarioService.getUsuariosActivos())
                .addAttribute("title","Ver incidencias");

        return mainController.homeRoute(model);
    }

    @GetMapping("/TipIncidenciaE")
    public String tipoIncidenciaE(@RequestParam long id, Model model){

        TipoIncidenciaViewmodel viewmodel = new TipoIncidenciaViewmodel();
        viewmodel.setTipoIncidencia(itemListService.getTipoIncidenciaById(id));
        viewmodel.setMessage("");

        model = setTemplateToModel(model,"incidencia","tipoIncidenciaE")
                .addAttribute("data", viewmodel)
                .addAttribute("title","Editar Tipo de Incidencia");
        return mainController.homeRoute(model);
    }

    /*======================================= POSTS ========================================*/



    /* INCIDENCIA */
    @PostMapping("/incidenciaC")
    public String postIncidenciaC(@ModelAttribute IncidenciaViewmodel viewmodel, BindingResult errors, Model model){
        incidenciaService.guardarIncidencia(viewmodel.getIncidencia(), SecurityContextHolder.getContext().getAuthentication().getName());
        return "redirect:/incidenciaL?=" + viewmodel.getIncidencia().getIdIncidencia();
    }

    @PostMapping("/incidenciaE")
    public String postIncidenciaE(@ModelAttribute IncidenciaViewmodel viewmodel, BindingResult errors, Model model){
        viewmodel.getIncidencia().setCreador(usuarioService.getUsuarioByUsername(viewmodel.getIncidencia().getCreador().getUsername()));
        incidenciaService.actualizarIncidencia(viewmodel.getIncidencia());

        return "redirect:/incidenciaL?=" + viewmodel.getIncidencia().getIdIncidencia();
    }

    /*CATEGORIA*/
    @PostMapping("/categoriaC")
    public String postCategoriaC(@ModelAttribute CategoriaDTO categoria, BindingResult errors, Model model){
        itemListService.guardar(categoria);
        return crearCategoria(model);
    }

    @PostMapping("/tipIncidenciaC")
    public String crearTipoIncidencia(@ModelAttribute TipoIncidenciaViewmodel viewmodel, BindingResult errors, Model model){
        itemListService.createTipoIncidencia(viewmodel.getTipoIncidencia());
        return "redirect:/tipIncidenciaL?=" + viewmodel.getTipoIncidencia().getId(); // pendiente con esto
    }


    /*USUARIO*/
    @PostMapping("/usuarioC")
    public String postUsuarioC(@ModelAttribute UsuarioViewmodel usuarioViewmodel, BindingResult errors, Model model) {
        usuarioService.guardarUsuario(usuarioViewmodel.getUsuario(),true);
        return "redirect:/usuarioL";
    }
    @PostMapping("/usuarioE")
    public String postUsuarioE(@ModelAttribute UsuarioViewmodel usuarioViewmodel, BindingResult errors, Model model) {
        usuarioService.guardarUsuario(usuarioViewmodel.getUsuario(),false);
        return "redirect:/usuarioL";
    }



    /*TIPO INCIDENCIA*/
    @PostMapping("/tipoIncidenciaC")
    public String crearTipoIncidencia(@ModelAttribute TipoIncidencia tipoIncid, BindingResult errors, Model model){
        itemListService.guardar(tipoIncid);
        return "redirect:/home";
    }

    private Model setTemplateToModel(Model model, String location, String template) {
        return model.addAttribute("location", "/" + location + "/").addAttribute("template", template);
    }
}
