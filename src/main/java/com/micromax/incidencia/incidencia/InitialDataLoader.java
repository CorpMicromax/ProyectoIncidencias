package com.micromax.incidencia.incidencia;

import com.micromax.incidencia.incidencia.domain.Privilegio;
import com.micromax.incidencia.incidencia.domain.Rol;
import com.micromax.incidencia.incidencia.domain.Usuario;
import com.micromax.incidencia.incidencia.repository.PrivilegioRepository;
import com.micromax.incidencia.incidencia.repository.RolRepository;
import com.micromax.incidencia.incidencia.repository.UsuarioRepository;
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