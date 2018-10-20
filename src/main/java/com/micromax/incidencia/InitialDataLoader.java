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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private RolRepository roleRepository;

    @Autowired
    private PrivilegioRepository privilegioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Privilegio readPrivilegio = createPrivilegioIfNotFound("READ_Privilegio");
        Privilegio writePrivilegio = createPrivilegioIfNotFound("WRITE_Privilegio");

        List<Privilegio> adminPrivilegios = Arrays.asList(readPrivilegio, writePrivilegio);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivilegios);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilegio));

        Rol adminRole = roleRepository.findByNombre("ROLE_ADMIN");
        Usuario usuario = new Usuario();
        usuario.setNombres("Javier");
        usuario.setApellidos("Letterer");
        usuario.setUsername("JLetterer");
        usuario.setPassword(passwordEncoder.encode("test"));
        usuario.setEmail("javier.letterer@micromax.com");
        usuario.setRoles(Arrays.asList(adminRole));
        usuario.setEnabled(true);
        userRepository.save(usuario);

        Usuario usuario2 = new Usuario();
        usuario2.setNombres("Karelis");
        usuario2.setApellidos("Ramirez");
        usuario2.setUsername("KRamirez");
        usuario2.setPassword(passwordEncoder.encode("holamundo0"));
        usuario2.setEmail("karelis.ramirez@micromax.com");
        usuario2.setRoles(Arrays.asList(adminRole));
        usuario2.setEnabled(true);
        userRepository.save(usuario2);
        alreadySetup = true;

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