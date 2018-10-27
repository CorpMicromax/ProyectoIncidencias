package com.micromax.incidencia.domain.entities;

import com.micromax.incidencia.domain.Desactivable;
import com.micromax.incidencia.domain.entities.users.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Encuesta extends Desactivable implements Serializable {

    private static final long serialVersionUID = 12L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_encuesta")
    private long idEncuesta;

    @Column(name = "puntaje")
    private byte puntaje;

    @Column(name = "mensaje", length = 4000)
    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "id_usuario_encuestado")
    private Usuario usuario;
}
