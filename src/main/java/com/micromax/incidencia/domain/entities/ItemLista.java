package com.micromax.incidencia.domain.entities;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@Data
public class ItemLista implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombre;

}
