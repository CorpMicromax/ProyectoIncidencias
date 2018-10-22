package com.micromax.incidencia.domain.entities.incidencias;

import com.micromax.incidencia.domain.entities.ItemLista;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity

public class TipoIncidencia extends ItemLista {

    private static final long serialVersionUID = 1905122041950251207L;
}
