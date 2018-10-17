package com.micromax.incidencia.incidencia.domain;

import com.micromax.incidencia.incidencia.Status;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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

    @OneToOne
    private User asignado;

}
