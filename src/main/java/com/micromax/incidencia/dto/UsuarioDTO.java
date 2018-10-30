package com.micromax.incidencia.dto;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.domain.entities.users.Cliente;
import com.micromax.incidencia.domain.entities.users.Tecnico;
import com.micromax.incidencia.domain.entities.users.Usuario;
import lombok.Data;

import javax.validation.constraints.Email;
import java.util.List;

@Data
public class UsuarioDTO {

    private Long id;
    private String username;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String password;
    @Email
    private String email;
    private Integer idRol;
    private String rif;
    private String direccion;
    private int capacidad;
    private String denominacionComercial;
    private String razonSocial;
    private Integer tipoUsuario;
    private List<Categoria> categorias;
    private List<Long> cats;

    public UsuarioDTO(){}

    public UsuarioDTO(Usuario usuario){
        this.id = usuario.getIdUsuario();
        this.tipoUsuario = 1;
        if(usuario instanceof Tecnico){
            this.capacidad = ((Tecnico) usuario).getCapacidad();
            this.categorias = ((Tecnico) usuario).getCategoriasTecnico();
            this.tipoUsuario = 2;
        }
        if(usuario instanceof Cliente){
            this.rif = ((Cliente) usuario).getRif();
            this.razonSocial = ((Cliente) usuario).getRazonSocial();
            this.denominacionComercial = ((Cliente) usuario).getDenominacionComercial();
            this.tipoUsuario = 3;
        }
        this.username = usuario.getUsername();
        this.apellidos = usuario.getApellidos();
        this.nombres = usuario.getNombres();
        this.telefono = usuario.getTelefono();
        this.email = usuario.getEmail();
        this.idRol = usuario.getRol().getIdRol();
    }
}
