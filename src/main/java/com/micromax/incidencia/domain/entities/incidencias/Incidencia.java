package com.micromax.incidencia.domain.entities.incidencias;

import com.micromax.incidencia.domain.Desactivable;
import com.micromax.incidencia.domain.Status;
import com.micromax.incidencia.domain.entities.Encuesta;
import com.micromax.incidencia.domain.entities.users.Tecnico;
import com.micromax.incidencia.domain.entities.users.Usuario;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Entity
public class Incidencia extends Desactivable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id_incidencia")
    private long idIncidencia;

    private String titulo;
    private String descripcion;
    private LocalDateTime creacion;
    private LocalDateTime resolucion;
    private int peso;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @OneToOne
    private Categoria categoria;

    @OneToOne
    private Usuario creador;

    @OneToOne
    private TipoIncidencia tipoIncidencia;

    private String tiempoEstimado;

    @ManyToMany
    @JoinTable(
            name = "asignaciones",
            joinColumns = @JoinColumn(
                    name = "id_incidencia", referencedColumnName = "id_incidencia"),
            inverseJoinColumns = @JoinColumn(
                    name = "id_usuario", referencedColumnName = "id_usuario"))
    private Collection<Tecnico> asignados;

    @OneToMany
    @JoinTable(
            name = "incidencia_comentario",
            joinColumns = @JoinColumn(
                    name = "id_incidencia", referencedColumnName = "id_incidencia"),
            inverseJoinColumns = @JoinColumn(
                    name = "id_comentario", referencedColumnName = "id_comentario"))
    private Collection<Comentario> comentarios;

    @OneToOne
    private Encuesta encuesta;

}
