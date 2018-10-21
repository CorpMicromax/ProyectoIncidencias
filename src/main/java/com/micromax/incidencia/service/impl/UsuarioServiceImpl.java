package com.micromax.incidencia.service.impl;

import com.micromax.incidencia.domain.entities.users.Rol;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.repository.PrivilegioRepository;
import com.micromax.incidencia.repository.RolRepository;
import com.micromax.incidencia.repository.UsuarioRepository;
import com.micromax.incidencia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PrivilegioRepository privilegioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario findUserByEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    public void saveUser(Usuario user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Rol userRole = rolRepository.findByNombre("ROLE_USER");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        usuarioRepository.save(user);
    }

    public Usuario getUsuarioByUsername(String username){
        return usuarioRepository.findByUsername(username).orElse(null);
    }

}
