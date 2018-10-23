package com.micromax.incidencia.viewmodel;

import com.micromax.incidencia.domain.entities.users.Rol;
import com.micromax.incidencia.domain.entities.users.Usuario;
import lombok.Data;

import java.util.List;

@Data
public class UsuarioViewmodel {
    private Usuario usuario;

    private List<Rol> roles;
    private String message;

}
