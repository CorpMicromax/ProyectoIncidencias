package com.micromax.incidencia.dto;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import lombok.Data;
import org.springframework.lang.Nullable;


@Data
public class CategoriaDTO {

    private String nombre;
    @Nullable
    private Categoria padre;
}
