package com.micromax.incidencia.viewmodel;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.domain.entities.users.Rol;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.dto.UsuarioDTO;
import lombok.Data;

import java.util.List;

@Data
public class UsuarioViewmodel {
    private UsuarioDTO usuarioDTO;
    private Usuario usuario;
    private List<Rol> roles;
    private String message;
    private List<Categoria> categorias;

}
