package com.micromax.incidencia.domain.entities.users;

import com.micromax.incidencia.domain.Desactivable;
import com.micromax.incidencia.domain.entities.incidencias.Comentario;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Inheritance
@DiscriminatorColumn(name = "tipo_usuario")
@Table(name="usuario")
public class Usuario extends Desactivable implements Serializable {

    private static final long serialVersionUID = 10L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_usuario", nullable = false, unique = true, precision = 3)
    private long idUsuario;

    @NotEmpty
    @Column(name="nombres", nullable = false)
    private String nombres;

    @Column(name="apellidos", nullable = false)
    private String apellidos;

    @Email(message = "*Por favor introduzca un email valido")
    @NotEmpty(message = "*Por favor introduzca un email")
    @Column(name="email", nullable = false)
    @NaturalId
    private String email;

    @NotEmpty(message = "*Por favor introduzca un nombre de usuario")
    @Column(name="username", nullable = false)
    private String username;

    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    @Column(name="password", nullable = false)
    private String password;

    @Column(name="direccion")
    private String direccion;

    @Column(name="telefono")
    private String telefono;

    @Column(name="tokenExpired", nullable = false)
    private boolean tokenExpired;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_rol", nullable = false)
    private Rol rol;

    @OneToMany
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "creador", cascade = CascadeType.ALL)
    private List<Incidencia> incidencias;


}
