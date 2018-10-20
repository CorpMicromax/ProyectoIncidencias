package com.micromax.incidencia.incidencia.domain.entities.incidencias;

import com.micromax.incidencia.incidencia.domain.entities.ItemLista;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Entity
public class Categoria extends ItemLista {

    private int nivel;

    @OneToOne
    private Categoria padre;
}
