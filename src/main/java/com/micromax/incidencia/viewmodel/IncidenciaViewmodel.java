package com.micromax.incidencia.viewmodel;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import lombok.Data;

import java.util.List;

@Data
public class IncidenciaViewmodel {
    private Incidencia incidencia;
    private String message;
    private List<Categoria> categorias;
}
