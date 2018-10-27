package com.micromax.incidencia.dto;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import lombok.Data;


@Data
public class CategoriaDTO {

    private String nombre;
    private Categoria padre;
}
