package com.micromax.incidencia;

import com.micromax.incidencia.domain.Constants;
import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.domain.entities.incidencias.TipoIncidencia;
import com.micromax.incidencia.domain.entities.users.Cliente;
import com.micromax.incidencia.domain.entities.users.Rol;
import com.micromax.incidencia.domain.entities.users.Tecnico;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.dto.CategoriaDTO;
import com.micromax.incidencia.repository.RolRepository;
import com.micromax.incidencia.service.ItemListService;
import com.micromax.incidencia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolRepository roleRepository;

    @Autowired
    private ItemListService itemListService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        Rol adminRole = crearRolSiNoExiste(Constants.ADMINROLE);
        Rol tecnico = crearRolSiNoExiste(Constants.TECHROLE);
        Rol cliente = crearRolSiNoExiste(Constants.CLIENTROLE);

        createUsuarioIfNotFound(construirUsuario(0,
                "Administrador",
                "Absoluto",
                "Admin",
                "admin",
                "Admin@micromax.com",
                adminRole));

        createUsuarioIfNotFound(construirUsuario(1,
                "Tecnico",
                "Uno",
                "TechUno",
                "admin",
                "Tecnico@micromax.com",
                tecnico));

        createUsuarioIfNotFound(construirUsuario(1,
                "Karelis",
                "Ramirez",
                "KRamirez",
                "admin",
                "karelis.ramirez@micromax.com",
                adminRole
        ));

        Usuario c = new Cliente();
        c.setNombres("Empresa");
        c.setApellidos("Cliente");
        c.setUsername("cliente");
        c.setPassword("cliente");
        c.setEmail("cliente@micromax.com");
        c.setRol(cliente);
        c.setHabilitado(true);
        createUsuarioIfNotFound(c);


        Categoria problema = crearCategoriaSiNoExiste("Problema", null);


        Categoria hardware = crearCategoriaSiNoExiste("Hardware",  problema);
        Categoria software = crearCategoriaSiNoExiste("Software",  problema);
        Categoria servicios = crearCategoriaSiNoExiste("Servicios",  problema);

        crearCategoriaSiNoExiste("Disco duro",  hardware);
        crearCategoriaSiNoExiste("Memorias RAM",  hardware);
        crearCategoriaSiNoExiste("Encendido",  hardware);
        crearCategoriaSiNoExiste("Video",  hardware);
        crearCategoriaSiNoExiste("Audio",  hardware);
        crearCategoriaSiNoExiste("Impresora",  hardware);

        crearCategoriaSiNoExiste("Sistema Operativo",  software);
        crearCategoriaSiNoExiste("Office 2007",  software);
        crearCategoriaSiNoExiste("Antivirus",  software);
        crearCategoriaSiNoExiste("Instalacion de programas",  software);


        crearCategoriaSiNoExiste("Reposicion consumible",  servicios);
        crearCategoriaSiNoExiste("Instalacion de red",  servicios);

        crearTipoIncidenciaSiNoExiste("En sitio");
        crearTipoIncidenciaSiNoExiste("Remoto");

        alreadySetup = true;
    }

    @Transactional
    public void crearTipoIncidenciaSiNoExiste(String nombre){
        TipoIncidencia tipoIncidencia1 = new TipoIncidencia();
        tipoIncidencia1.setNombre(nombre);
        if(!itemListService.existeTipoIncidencia(tipoIncidencia1)) {
            itemListService.guardar(tipoIncidencia1);
        }
    }
    @Transactional
    public Categoria crearCategoriaSiNoExiste(String nombre, @Nullable Categoria padre) {
        CategoriaDTO cat = new CategoriaDTO();
        cat.setNombre(nombre);
        cat.setPadre(padre);
        if(!itemListService.existeCategoria(cat.getNombre())){
            return itemListService.guardar(cat);
        }
        return itemListService.getCategoria(nombre);
    }

    @Transactional
    public void createUsuarioIfNotFound(Usuario usuario){
        if(!usuarioService.existeUsuario(usuario.getUsername())){
            usuarioService.guardarUsuario(usuario, true);
        }
    }

    @Transactional
    public Rol crearRolSiNoExiste(String nombre) {

        Rol rol = roleRepository.findByNombre(nombre);
        if (rol == null) {
            rol = new Rol();
            rol.setNombre(nombre);
            roleRepository.save(rol);
        }
        return rol;
    }

    private Usuario construirUsuario(int tipo, String nombre, String apellido, String username, String pass, String email, Rol rol){
        Usuario u = new Usuario();
        if (tipo == 1) {
            u = new Tecnico();
        }
        if (tipo == 2){
            u = new Cliente();
        }
        u.setNombres(nombre);
        u.setApellidos(apellido);
        u.setUsername(username);
        u.setPassword(pass);
        u.setEmail(email);
        u.setRol(rol);
        u.setHabilitado(true);
        return u;
    }
}