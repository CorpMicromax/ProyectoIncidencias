package com.micromax.incidencia.controller;

import com.micromax.incidencia.domain.Constants;
import com.micromax.incidencia.domain.Status;
import com.micromax.incidencia.domain.entities.Encuesta;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Tecnico;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.dto.IncidenciaDTO;
import com.micromax.incidencia.service.HistoricoService;
import com.micromax.incidencia.service.IncidenciaService;
import com.micromax.incidencia.service.ItemListService;
import com.micromax.incidencia.service.UsuarioService;
import com.micromax.incidencia.viewmodel.EncuestaViewmodel;
import com.micromax.incidencia.viewmodel.IncidenciaViewmodel;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    private HistoricoService historicoService;

    //@Autowired
    //private EncuestaService encuestaService;

    /*-------------------------------------------- INCIDENCIA -------------------------------------------------*/
    @GetMapping("/incidenciaC")
    public String incidenciaC(Model model){
        IncidenciaViewmodel viewmodel = new IncidenciaViewmodel();
        viewmodel.setIncidenciaDTO(new IncidenciaDTO());
        viewmodel.setMessage("");
        viewmodel.setCategorias(itemListService.getCategoriaByNivel(1));
        viewmodel.setTipoIncidencias(itemListService.getAllTipoIncidencias());

        model = setTemplateToModel(model, INCIDENCIA,"incidenciaC")
                .addAttribute(Constants.DATA, viewmodel)
                .addAttribute(TITLE,"Crear Incidencia");
        return mainController.homeRoute(model);
    }

    @GetMapping(value = "/incidenciaV/{idIncidencia}")
    public String incidenciaV(@PathVariable String idIncidencia, Model model){
        IncidenciaViewmodel viewmodel = new IncidenciaViewmodel();
        viewmodel.setIncidencia(incidenciaService.getIncidenciaById(idIncidencia));
        viewmodel.setIncidenciaDTO(new IncidenciaDTO(incidenciaService.getIncidenciaById(idIncidencia)));
        viewmodel.setHistorico(historicoService.getHistoricoByIncidencia(incidenciaService.getIncidenciaById(idIncidencia)));
        model = setTemplateToModel(model, INCIDENCIA,"incidenciaV")
                .addAttribute(Constants.DATA, viewmodel)
                .addAttribute(TITLE,"Detalles Incidencia: " + viewmodel.getIncidencia().getIdIncidencia());
        return mainController.homeRoute(model);
    }

    @GetMapping("/incidenciaE")
    public String incidenciaE(@RequestParam String id, Model model){

        IncidenciaViewmodel viewmodel = new IncidenciaViewmodel();
        viewmodel.setIncidenciaDTO(new IncidenciaDTO(incidenciaService.getIncidenciaById(id)));
        viewmodel.setTecnicos(usuarioService.getTecnicos());
        viewmodel.setMessage("");
        viewmodel.setTipoIncidencias(itemListService.getAllTipoIncidencias());
        viewmodel.setCategorias(itemListService.getCategoriaByNivel(1));

        model = setTemplateToModel(model, INCIDENCIA,"incidenciaE")
                .addAttribute(Constants.DATA, viewmodel)
                .addAttribute(TITLE,"Editar Incidencia");
        return mainController.homeRoute(model);
    }

    @GetMapping("/incidenciaL")
    public String incidenciaL(@RequestParam(value = "id", required = false) String id, Model model){
        List<Incidencia> incidencias;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario u = usuarioService.getUsuarioByUsername(auth.getName());
        String rol = obtenerRol();
        incidencias = incidenciaService.obtenerIncidenciasPorCreador(u);
        if(rol.equalsIgnoreCase("ROLE_ADMIN")){
            incidencias = incidenciaService.getIncidencias();
        }else if( u instanceof Tecnico){
            incidencias.addAll(ObjectUtils.defaultIfNull(((Tecnico) u).getAsignaciones(), new ArrayList<>()) );
        }

        model = setTemplateToModel(model, INCIDENCIA,"incidenciaL")
                .addAttribute("incidencias", incidencias)
                .addAttribute(TITLE,"Incidencias");
        if(id != null){
            model.addAttribute("relevant", id);
        }
        return mainController.homeRoute(model);
    }

    @GetMapping("/encuesta/{idIncidencia}")
    public String incidenciaCerrar(@PathVariable String idIncidencia, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario u = usuarioService.getUsuarioByUsername(auth.getName());
        IncidenciaDTO dto = new IncidenciaDTO();
        dto.setStatus(Status.CERRADA);
        dto.setId(idIncidencia);
        incidenciaService.cambioStatus(dto, u);

        EncuestaViewmodel viewmodel = new EncuestaViewmodel();
        viewmodel.setEncuesta(new Encuesta());
        viewmodel.setIdIncidencia(idIncidencia);
        if(u.getRol().getNombre().equalsIgnoreCase(Constants.ADMINROLE)){
            return mainController.homeRoute(model);
        }

        model = setTemplateToModel(model, INCIDENCIA,"encuesta")
                .addAttribute(Constants.DATA, viewmodel)
                .addAttribute(TITLE,"Encuesta de satisfacción Micromax");
        return "incidencia/encuesta";
    }

    @GetMapping("/reopen/{idIncidencia}")
    public String reabrirIncidencia(@PathVariable String idIncidencia, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario u = usuarioService.getUsuarioByUsername(auth.getName());
        IncidenciaDTO dto = new IncidenciaDTO();
        dto.setStatus(Status.REABIERTA);
        dto.setId(idIncidencia);
        incidenciaService.cambioStatus(dto, u);
        return incidenciaV(idIncidencia, model);
    }

    /*=======================================+======== POSTS ========================================*/

    /* INCIDENCIA */
    @PostMapping("/incidenciaC")
    public String postIncidenciaC(@ModelAttribute IncidenciaViewmodel viewmodel, BindingResult errors, Model model){
        incidenciaService.crearIncidencia(viewmodel.getIncidenciaDTO(), usuarioActual());
        return "redirect:/incidenciaL";
    }

    @PostMapping("/incidenciaE")
    public String postIncidenciaE(@ModelAttribute IncidenciaViewmodel viewmodel, BindingResult errors, Model model){
        incidenciaService.actualizarIncidencia(viewmodel.getIncidenciaDTO(), usuarioActual());
        return "redirect:/incidenciaV/"+ viewmodel.getIncidenciaDTO().getId();
    }

    @PostMapping("/incidenciaV")
    public String postIncidenciaV(@ModelAttribute IncidenciaViewmodel viewmodel, BindingResult errors, Model model){
        incidenciaService.comentar(viewmodel.getComentario(), viewmodel.getIncidenciaDTO(), usuarioActual());
        return "redirect:/incidenciaV/" + viewmodel.getIncidenciaDTO().getId();
    }

    @PostMapping("/encuesta")
    public String crearEncuesta(@ModelAttribute EncuestaViewmodel viewmodel, BindingResult errors, Model model){

        //encuestaService.crearEncuesta(usuarioActual(), incidenciaService.getIncidenciaById(viewmodel.getIdIncidencia()), viewmodel.getEncuesta());
        return "redirect:/incidenciaL";
    }

    private String obtenerRol(){
        return usuarioActual().getRol().getNombre();
    }

    private Usuario usuarioActual(){
        return usuarioService.getUsuarioByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
