package com.micromax.incidencia;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.domain.entities.users.Privilegio;
import com.micromax.incidencia.domain.entities.users.Rol;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.repository.CategoriaRepository;
import com.micromax.incidencia.repository.PrivilegioRepository;
import com.micromax.incidencia.repository.RolRepository;
import com.micromax.incidencia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private RolRepository roleRepository;

    @Autowired
    private PrivilegioRepository privilegioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Privilegio readPrivilegio = createPrivilegioIfNotFound("READ_Privilegio");
        Privilegio writePrivilegio = createPrivilegioIfNotFound("WRITE_Privilegio");

        List<Privilegio> adminPrivilegios = Arrays.asList(readPrivilegio, writePrivilegio);
        Rol adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivilegios);
        createRoleIfNotFound("ROLE_USER", Collections.singletonList(readPrivilegio));

        Usuario usuario = new Usuario();
        usuario.setNombres("Javier");
        usuario.setApellidos("Letterer");
        usuario.setUsername("JLetterer");
        usuario.setPassword(passwordEncoder.encode("admin"));
        usuario.setEmail("javier.letterer@micromax.com");
        usuario.setRoles(Collections.singletonList(adminRole));
        usuario.setEnabled(true);
        createUsuarioIfNotFound(usuario);

        Usuario usuario2 = new Usuario();
        usuario2.setNombres("Karelis");
        usuario2.setApellidos("Ramirez");
        usuario2.setUsername("KRamirez");
        usuario2.setPassword(passwordEncoder.encode("admin"));
        usuario2.setEmail("karelis.ramirez@micromax.com");
        usuario2.setRoles(Collections.singletonList(adminRole));
        usuario2.setEnabled(true);
        createUsuarioIfNotFound(usuario2);


        Categoria dad = createCategoriaIfNotFound("Problema",0,null);
        Categoria hard = createCategoriaIfNotFound("Hardware",1,dad);
        Categoria soft = createCategoriaIfNotFound("Software", 1,dad);
        Categoria otro = createCategoriaIfNotFound("Otro", 1,dad);
        createCategoriaIfNotFound("Impresora",2, hard);
        createCategoriaIfNotFound("Computadora",2, hard);
        createCategoriaIfNotFound("Sistema Operativo", 2, soft);
        createCategoriaIfNotFound("Office", 2, soft);
        createCategoriaIfNotFound("Cableado de Red", 2, otro);
        createCategoriaIfNotFound("Exorcismo de fotocopiadora", 2, otro);

        alreadySetup = true;
    }

    @Transactional
    private Privilegio createPrivilegioIfNotFound(String name) {

        Privilegio privilegio = privilegioRepository.findByNombre(name);
        if (privilegio == null) {
            privilegio = new Privilegio();
            privilegio.setNombre(name);
            privilegioRepository.save(privilegio);
        }
        return privilegio;
    }

    @Transactional
    private void createUsuarioIfNotFound(Usuario usuario){
        if(!userRepository.findByUsername(usuario.getUsername()).isPresent()){
            userRepository.save(usuario);
        }
    }

    @Transactional
    private Categoria createCategoriaIfNotFound(String name, int nivel, Categoria padre) {

        Categoria categoria = categoriaRepository.findByNombre(name);
        if (categoria == null) {
            categoria = new Categoria();
            categoria.setNivel(nivel);
            categoria.setPadre(padre);
            categoria.setNombre(name);
            categoriaRepository.save(categoria);
        }
        return categoria;
    }

    @Transactional
    private Rol createRoleIfNotFound(
            String nombre, Collection<Privilegio> privilegios) {

        Rol rol = roleRepository.findByNombre(nombre);
        if (rol == null) {
            rol = new Rol();
            rol.setNombre(nombre);
            rol.setPrivilegios(privilegios);
            roleRepository.save(rol);
        }
        return rol;
    }
}