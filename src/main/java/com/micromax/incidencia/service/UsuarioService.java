package com.micromax.incidencia.service;

import com.micromax.incidencia.domain.entities.users.Rol;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.dto.UsuarioDTO;

import java.util.Collection;
import java.util.List;


public interface UsuarioService {

    Usuario getUsuarioByUsername(String username);

    void guardarUsuario(UsuarioDTO usuarioDTO, boolean nuevo);

    void guardarUsuario(Usuario usuario, boolean nuevo);

    Usuario findUserByEmail(String email);

    List<Rol> getRoles();

    Collection<Usuario> getUsuariosActivos();

    Usuario getUsuarioById(long id);

    boolean borrarUsuario(Long id);

    Usuario findUsuarioByUsername(String username);

    boolean existeUsuario(String username);
}
