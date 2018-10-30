package com.micromax.incidencia.domain.entities.users;

import com.micromax.incidencia.domain.Desactivable;
import com.micromax.incidencia.domain.entities.incidencias.Comentario;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Inheritance
@DiscriminatorColumn(name = "tipo_usuario")
@Table(name="usuario")
@ToString(exclude = {"comentarios", "incidencias", "rol"})
@DiscriminatorValue("U")
public class Usuario extends Desactivable implements Serializable, UserDetails {

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
    private String email;

    @NotEmpty(message = "*Por favor introduzca un nombre de usuario")
    @Column(name="username", nullable = false, unique = true)
    private String username;


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


    public String getPrimerNombreYPrimerApellido(){
        Pattern pattern = Pattern.compile("^([\\w\\-]+)");

        Matcher matchNombre = pattern.matcher(nombres);
        Matcher matchApellido = pattern.matcher(apellidos);
        if(matchNombre.matches() && matchApellido.matches()) return matchNombre.group(1)+" "+matchApellido.group(1);
        return username;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(rol.getNombre()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return isHabilitado();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isHabilitado();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isHabilitado();
    }
}
