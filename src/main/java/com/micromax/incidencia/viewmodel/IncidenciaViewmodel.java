package com.micromax.incidencia.viewmodel;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.dto.IncidenciaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class IncidenciaViewmodel {
    private IncidenciaDTO incidencia;
    private String message;
    private List<Categoria> categorias;
}
