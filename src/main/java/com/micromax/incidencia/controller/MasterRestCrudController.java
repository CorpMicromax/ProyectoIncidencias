package com.micromax.incidencia.controller;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.domain.entities.users.Tecnico;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.service.IncidenciaService;
import com.micromax.incidencia.service.ItemListService;
import com.micromax.incidencia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MasterRestCrudController {

    @Autowired
    private ItemListService itemListService;

    @Autowired
    private IncidenciaService incidenciaService;

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/rest/incidencia/getCategoriasDos")
    public List<Categoria> categoriasDos(@RequestParam long id) {
        return itemListService.getCategoriasByPadre(id);
    }

    @GetMapping("/rest/incidencia/getCategoriasUno")
    public List<Categoria> categoriasUno() {
        return itemListService.getCategoriaByNivel(1);
    }

    @GetMapping("/rest/incidencia/incidenciaD")
    public boolean incidenciaD( String id, Usuario user){
        return incidenciaService.borrarIncidencia(id, usuarioActual());
    }

    @GetMapping("/rest/incidencia/usuarioD")
    public boolean usuarioD( Long id){
        return usuarioService.borrarUsuario(id);
    }

    @GetMapping("rest/incidencia/getTecnicos/{catId}")
    public List<Tecnico> getTecnicosPorCat(@PathVariable long catId){
        return usuarioService.getTecnicosPorCategoria(catId);
    }

    private Usuario usuarioActual(){
        return usuarioService.getUsuarioByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
