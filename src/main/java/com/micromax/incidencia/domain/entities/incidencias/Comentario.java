package com.micromax.incidencia.domain.entities.incidencias;

import com.micromax.incidencia.domain.Desactivable;
import com.micromax.incidencia.domain.entities.users.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "comentario")
public class Comentario extends Desactivable implements Serializable {

    @Transient
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private long idComentario;

    @ManyToOne
    @JoinColumn(name = "id_incidencia")
    private Incidencia incidencia;

    @ManyToOne
    @JoinColumn(name = "id_usuario_creador")
    private Usuario autor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion")
    private Date creacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_ultimaEdicion")
    private Date ultimaEdicion;

    @Column(name = "contenido", length = 4000)
    private String contenido;
}
