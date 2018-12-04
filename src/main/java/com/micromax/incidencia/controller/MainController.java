package com.micromax.incidencia.controller;

import com.micromax.incidencia.domain.Constants;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.service.IncidenciaService;
import com.micromax.incidencia.service.ReportService;
import com.micromax.incidencia.service.UsuarioService;
import com.micromax.incidencia.viewmodel.DashboardViewmodel;
import com.micromax.incidencia.viewmodel.HomeViewmodel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = "admin/reportes")
    public void export(ModelAndView model, HttpServletResponse response) throws IOException, JRException, SQLException {
        JasperPrint jasperPrint = null;

        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"ReporteTiempoRespuesta.pdf\""));

        OutputStream out = response.getOutputStream();
        jasperPrint = reportService.exportPdfFile();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
    }

}
