package com.micromax.incidencia.domain.entities.incidencias;

import com.micromax.incidencia.domain.entities.users.Usuario;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_comentario")
    private long idComentario;

    @OneToOne
    private Usuario autor;

    private LocalDateTime creacion;
    private LocalDateTime ultimaEdicion;

    private String contenido;
}
