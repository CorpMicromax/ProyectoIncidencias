package com.micromax.incidencia.service.impl;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.domain.entities.users.Cliente;
import com.micromax.incidencia.domain.entities.users.Rol;
import com.micromax.incidencia.domain.entities.users.Tecnico;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.dto.UsuarioDTO;
import com.micromax.incidencia.repository.*;
import com.micromax.incidencia.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PrivilegioRepository privilegioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Usuario findUserByEmail(String email) {
        return usuarioRepository.findByEmailAndHabilitado(email, true).orElse(null);
    }

    @Override
    public List<Rol> getRoles() {
        return (ArrayList<Rol>) rolRepository.findAll();
    }

    @Override
    public Collection<Usuario> getUsuariosActivos() {
        return usuarioRepository.findAllByHabilitado(true);
    }

    @Override
    public Usuario getUsuarioById(long id) {
        Tecnico t = tecnicoRepository.findTecnicoByIdUsuarioAndHabilitado(id, true);
        Cliente c = clienteRepository.findClienteByIdUsuarioAndHabilitado(id,true);
        if(t != null)return t;
        if(c != null)return c;
        return usuarioRepository.findByIdUsuarioAndHabilitado(id, true);
    }

    @Override
    public boolean borrarUsuario(Long id) {
        Usuario u = usuarioRepository.findByIdUsuarioAndHabilitado(id, true);
        if(u != null) {
            u.setHabilitado(false);
            log.info("Eliminada incidencia con id %d", id);
            return usuarioRepository.save(u) != null;
        }
        return false;
    }

    @Override
    public Usuario findUsuarioByUsername(String username) {

        return usuarioRepository.findUsuarioByUsernameAndHabilitado(username, true);
    }

    @Override
    public boolean existeUsuario(String username) {
        return usuarioRepository.existsByUsernameAndHabilitado(username, true);
    }

    public void guardarUsuario(Usuario usuario, boolean bool){
        if(bool)usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
    }

    public void asignarTecnico(UsuarioDTO dto){
        Usuario u = usuarioRepository.findByIdUsuarioAndHabilitado(dto.getId(),true);
        u.setNombres(ObjectUtils.defaultIfNull(dto.getNombres(), u.getNombres()));
        u.setApellidos(ObjectUtils.defaultIfNull(dto.getApellidos(), u.getApellidos()));
        u.setDireccion(ObjectUtils.defaultIfNull(dto.getDireccion(), u.getDireccion()));
        u.setRol(ObjectUtils.defaultIfNull(
                rolRepository.findByIdRol(dto.getIdRol()),
                u.getRol()));

        u.setTelefono(ObjectUtils.defaultIfNull(dto.getTelefono(), u.getTelefono()));
        u.setEmail(ObjectUtils.defaultIfNull(dto.getEmail(), u.getEmail()));
        if(u instanceof Cliente){
            ((Cliente) u).setRif(ObjectUtils.defaultIfNull(dto.getRif(), ((Cliente) u).getRif()));
            ((Cliente) u).setRazonSocial(ObjectUtils.defaultIfNull(dto.getRazonSocial(), ((Cliente) u).getRazonSocial()));
            ((Cliente) u).setDenominacionComercial(ObjectUtils.defaultIfNull(dto.getDenominacionComercial(), ((Cliente) u).getDenominacionComercial()));
        }
        if(u instanceof Tecnico){
            ((Tecnico) u).setCapacidad(ObjectUtils.defaultIfNull(dto.getCapacidad(), ((Tecnico) u).getCapacidad()));
        }
        usuarioRepository.save(u);
    }

    @Override
    public List<Tecnico> getTecnicos() {
        return (List<Tecnico>) tecnicoRepository.findAllByHabilitado(true);
    }

    @Override
    @Transactional
    public void asignarTecnico(Tecnico t) {
        if(t!=null)tecnicoRepository.save(t);
    }

    @Override
    @Transactional
    public void guardarUsuario(UsuarioDTO usuarioDTO, boolean nuevo) {
        Usuario usuario;
        switch (usuarioDTO.getTipoUsuario()){
            case 1: usuario = new Usuario();
                    break;
            case 2: usuario = new Tecnico();
                    List<Categoria> cats = (List<Categoria>) categoriaRepository.findAllById(usuarioDTO.getCats());
                    ((Tecnico) usuario).setCategoriasTecnico(cats);
                    break;
            case 3: usuario = new Cliente();
                    ((Cliente) usuario).setRif(usuarioDTO.getRif());
                    ((Cliente) usuario).setDenominacionComercial(usuarioDTO.getDenominacionComercial());
                    ((Cliente) usuario).setRazonSocial(usuarioDTO.getRazonSocial());
                    break;
            default: usuario = new Usuario();
                    break;
        }
        usuario.setNombres(usuarioDTO.getNombres());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setEmail(usuarioDTO.getEmail());
        if(nuevo){
            usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        }else{
            Usuario u = usuarioRepository.findByIdUsuarioAndHabilitado(usuarioDTO.getId(),true);
            usuario.setPassword(u.getPassword());
            usuario.setIdUsuario(u.getIdUsuario());
        }

        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setRol(rolRepository.findByIdRol(usuarioDTO.getIdRol()));
        usuario.setDireccion(usuarioDTO.getDireccion());
        usuario.setHabilitado(true);
        usuarioRepository.save(usuario);
    }


    public Usuario getUsuarioByUsername(String username){
        return usuarioRepository.findUsuarioByUsernameAndHabilitado(username, true);
    }

}
