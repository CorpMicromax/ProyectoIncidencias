package com.micromax.incidencia.domain.entities;

import com.micromax.incidencia.domain.Desactivable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public class ItemLista extends Desactivable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "nombre")
    private String nombre;

}
