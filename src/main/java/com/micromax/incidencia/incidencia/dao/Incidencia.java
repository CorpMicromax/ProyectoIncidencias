package com.micromax.incidencia.incidencia.dao;

import com.micromax.incidencia.incidencia.Status;
import com.micromax.incidencia.incidencia.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Incidencia {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String titulo;
    private String descripcion;
    private Status status;

    @OneToOne
    private User asignado;

}
