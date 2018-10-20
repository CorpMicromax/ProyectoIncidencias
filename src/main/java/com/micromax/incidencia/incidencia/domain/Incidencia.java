package com.micromax.incidencia.incidencia.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Entity
public class Incidencia implements Serializable {

    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String titulo;
    private String descripcion;
    private Status status;
    private LocalDateTime creacion;
    private LocalDateTime resolucion;
    private int peso;

    @OneToOne
    private Usuario creador;


    private String tiempoEstimado;

    @OneToMany
    @JoinTable(
            name = "incidencia_asignado",
            joinColumns = @JoinColumn(
                    name = "id_incidencia", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "id_asignado", referencedColumnName = "id"))
    private Collection<Usuario> asignado;

    @OneToOne
    private TipoIncidencia tipo;
}
