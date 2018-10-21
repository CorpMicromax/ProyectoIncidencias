package com.micromax.incidencia.domain.entities.users;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collection;


@Data
@Entity
@Inheritance
@DiscriminatorColumn(name = "tipo_usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_usuario")
    private long idUsuario;

    @NotEmpty
    private String nombres;

    @NotEmpty
    private String apellidos;

    @Email(message = "*Por favor introduzca un email valido")
    @NotEmpty(message = "*Por favor introduzca un email")
    private String email;

    @NotEmpty(message = "*Por favor introduzca un nombre de usuario")
    private String username;

    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    private String direccion;
    private String telefono;
    private boolean enabled;
    private boolean tokenExpired;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(
                    name = "id_usuario", referencedColumnName = "id_usuario"),
            inverseJoinColumns = @JoinColumn(
                    name = "id_rol", referencedColumnName = "id_rol"))
    private Collection<Rol> roles;

}
