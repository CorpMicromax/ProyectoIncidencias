package com.micromax.incidencia.incidencia.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class User implements Serializable {


    private static final long serialVersionUID = 1905122041950251209L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String nombres;
    private String apellidos;
    private String email;

}
