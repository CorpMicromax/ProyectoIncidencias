package com.micromax.incidencia.domain.entities.users;

import com.micromax.incidencia.domain.Desactivable;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
@Entity
@Inheritance
@DiscriminatorColumn(name = "tipo_usuario")
public class Usuario extends Desactivable {

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

    @OneToOne(cascade = CascadeType.ALL)
    private Rol rol;

}
