package com.micromax.incidencia.service.impl;

import com.micromax.incidencia.domain.entities.users.Rol;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.repository.PrivilegioRepository;
import com.micromax.incidencia.repository.RolRepository;
import com.micromax.incidencia.repository.UsuarioRepository;
import com.micromax.incidencia.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
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

    @Override
    public List<Rol> getRoles() {
        return (ArrayList<Rol>) rolRepository.findAllByActiva(true);
    }

    @Override
    public Collection<Usuario> getUsuariosActivos() {
        return usuarioRepository.findAllByActiva(true);
    }

    @Override
    public Usuario getUsuarioById(long id) {
        return usuarioRepository.findByIdUsuarioAndActiva(id, true);
    }

    @Override
    public boolean borrarUsuario(Long id) {
        Usuario u = usuarioRepository.findByIdUsuarioAndActiva(id, true);
        if(u != null) {
            u.setActiva(false);
            log.info("Eliminada incidencia con id %d", id);
            return usuarioRepository.save(u) != null;
        }
        return false;
    }



    public void guardarUsuario(Usuario user, boolean nuevo) {
        if(nuevo)user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Rol userRole = rolRepository.findByNombreAndActiva("ROLE_USER", true);
        user.setRol(userRole);
        usuarioRepository.save(user);
    }

    public Usuario getUsuarioByUsername(String username){
        return usuarioRepository.findByUsername(username).orElse(null);
    }

}
