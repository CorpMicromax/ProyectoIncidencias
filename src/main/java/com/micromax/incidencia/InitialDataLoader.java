package com.micromax.incidencia;

import com.micromax.incidencia.domain.entities.users.Cliente;
import com.micromax.incidencia.domain.entities.users.Permiso;
import com.micromax.incidencia.domain.entities.users.Rol;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.repository.CategoriaRepository;
import com.micromax.incidencia.repository.PrivilegioRepository;
import com.micromax.incidencia.repository.RolRepository;
import com.micromax.incidencia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UsuarioService usuarioService;

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
        Permiso admin_usuarios = crearPermisoSiNoExiste("admin_usuarios");
        Permiso admin_mantenimiento = crearPermisoSiNoExiste("admin_mantenimiento");
        Permiso admin_permisos = crearPermisoSiNoExiste("admin_permisos");
        Permiso incidencia_crear = crearPermisoSiNoExiste("incidencia_crear");
        Permiso incidencia_verPropias = crearPermisoSiNoExiste("incidencia_verPropias");
        Permiso incidencia_verTodas = crearPermisoSiNoExiste("incidencia_verTodas");
        Permiso incidencia_verAsignadas = crearPermisoSiNoExiste("incidencia_verAsignadas");
        Permiso incidencia_editar = crearPermisoSiNoExiste("incidencia_editar");
        Permiso incidencia_borrar = crearPermisoSiNoExiste("incidencia_borrar");
        Permiso incidencia_cambiarEstado = crearPermisoSiNoExiste("incidencia_cambiarEstado");
        Permiso incidencia_comentar = crearPermisoSiNoExiste("incidencia_comentar");
        Permiso incidencia_asignar = crearPermisoSiNoExiste("incidencia_asignar");
        Permiso todos = crearPermisoSiNoExiste("todos");

        Rol adminRole = crearRolSiNoExiste("ROLE_ADMIN", Collections.singletonList(todos));
        Rol tecnico = crearRolSiNoExiste("ROLE_TECH",
                Arrays.asList(incidencia_crear,
                    incidencia_editar,
                    incidencia_verAsignadas,
                    incidencia_verPropias)
        );
        Rol tecnicoAvanzado = crearRolSiNoExiste("ROLE_ADV_TECH",
                Arrays.asList(
                        incidencia_asignar,
                        incidencia_cambiarEstado,
                        incidencia_comentar,
                        incidencia_editar,
                        incidencia_verAsignadas,
                        incidencia_verPropias,
                        incidencia_verTodas)
        );
        Rol cliente = crearRolSiNoExiste("ROLE_CLIENT",
                Arrays.asList(
                        incidencia_crear,
                        incidencia_comentar,
                        incidencia_verPropias)
        );

        createUsuarioIfNotFound(construirUsuario(
                "Francisco",
                "Letterer",
                "FLetterer",
                "admin",
                "Javier.Darkona@gmail.com",
                adminRole));

        createUsuarioIfNotFound(construirUsuario(
                "Javier",
                "Letterer",
                "JLetterer",
                "admin",
                "Javier.Darkona@gmail.com",
                tecnico));

        createUsuarioIfNotFound(construirUsuario(
                "Karelis",
                "Ramirez",
                "KRamirez",
                "admin",
                "karelis.ramirez@micromax.com",
                adminRole
        ));

        Usuario c = new Cliente();
        c.setNombres("Empresa Cliente");
        c.setApellidos("");
        c.setUsername("cliente");
        c.setPassword("cliente");
        c.setEmail("cliente@micromax.com");
        c.setRol(cliente);
        c.setHabilitado(true);
        createUsuarioIfNotFound(c);

        alreadySetup = true;
    }

    @Transactional
    public Permiso crearPermisoSiNoExiste(String name) {

        Permiso permiso = privilegioRepository.findByNombre(name);
        if (permiso == null) {
            permiso = new Permiso();
            permiso.setNombre(name);
            privilegioRepository.save(permiso);
        }
        return permiso;
    }

    @Transactional
    public void createUsuarioIfNotFound(Usuario usuario){
        if(!usuarioService.existeUsuario(usuario.getUsername())){
            usuarioService.guardarUsuario(usuario, true);
        }
    }

    @Transactional
    public Rol crearRolSiNoExiste(
            String nombre, Collection<Permiso> permisos) {

        Rol rol = roleRepository.findByNombreAndHabilitado(nombre, true);
        if (rol == null) {
            rol = new Rol();
            rol.setNombre(nombre);
            rol.setPermisos(permisos);
            roleRepository.save(rol);
        }
        return rol;
    }

    private Usuario construirUsuario(String nombre, String apellido, String username, String pass, String email, Rol rol){
        Usuario usuario = new Usuario();
        usuario.setNombres(nombre);
        usuario.setApellidos(apellido);
        usuario.setUsername(username);
        usuario.setPassword(pass);
        usuario.setEmail(email);
        usuario.setRol(rol);
        usuario.setHabilitado(true);
        return usuario;
    }
}