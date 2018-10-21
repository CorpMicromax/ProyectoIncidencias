package com.micromax.incidencia.service;

import com.micromax.incidencia.domain.entities.users.Usuario;


public interface UsuarioService {

    Usuario getUsuarioByUsername(String username);

    public void saveUser(Usuario user);

    Usuario findUserByEmail(String email);
}
