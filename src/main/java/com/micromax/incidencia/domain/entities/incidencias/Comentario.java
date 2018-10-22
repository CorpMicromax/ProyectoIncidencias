package com.micromax.incidencia.domain.entities.incidencias;

import com.micromax.incidencia.domain.Desactivable;
import com.micromax.incidencia.domain.entities.users.Usuario;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Comentario extends Desactivable {

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
