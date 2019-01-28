package com.micromax.incidencia.controller;

import com.micromax.incidencia.config.ConfiguracionGeneral;
import com.micromax.incidencia.domain.Constants;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.service.IncidenciaService;
import com.micromax.incidencia.service.ReportService;
import com.micromax.incidencia.service.UsuarioService;
import com.micromax.incidencia.viewmodel.ConfigGeneralViewmodel;
import com.micromax.incidencia.viewmodel.DashboardViewmodel;
import com.micromax.incidencia.viewmodel.HomeViewmodel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

@Controller
public class MainController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private IncidenciaService incidenciaService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private ConfiguracionGeneral configuracionGeneral;

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

    /*@GetMapping(value = {"/admin/reportes"})
    public String reportes(Model model){
        HomeViewmodel viewmodel = new HomeViewmodel();
        model = setTemplateToModel(model, "","reportes")
                .addAttribute(Constants.DATA, viewmodel)
                .addAttribute(TITLE,"Reportes");
        return homeRoute(model);
    }*/

    @GetMapping(value = "/admin/reportes")
    public String reporte(Model model){

        model.addAttribute("location", "/").addAttribute("template", "reportes");

        return homeRoute(model);

    }

    @PostMapping(value = "/admin/reportes/generar", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public void export(int idReporte, ModelAndView model, HttpServletResponse response) throws IOException, JRException, SQLException {

        response.setContentType("application/x-download");
        if(idReporte == 2) {
            response.setHeader("Content-Disposition", "attachment; filename=\"ReporteTiempoRespuesta.pdf\"");
        }
        if(idReporte == 1){
            response.setHeader("Content-Disposition", "attachment; filename=\"ReporteHorasTrabajadas.pdf\"");
        }
        if(idReporte == 3){
            response.setHeader("Content-Disposition", "attachment; filename=\"ReporteActividad.pdf\"");
        }
        OutputStream out = response.getOutputStream();
        JasperPrint jasperPrint = reportService.exportPdfFile(idReporte);
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
    }

    @GetMapping(value = "/admin/config")
    public String config(Model model){
        model.addAttribute("location", "/").addAttribute("template", "config");
        model.addAttribute("configData", configuracionGeneral.obtenerViewModel());
        return homeRoute(model);
    }

    @PostMapping(value = "/admin/config")
    public String configPost(@ModelAttribute ConfigGeneralViewmodel viewmodel, Model model){
        configuracionGeneral.cambiarConfig(viewmodel);
        return config(model);
    }
}
