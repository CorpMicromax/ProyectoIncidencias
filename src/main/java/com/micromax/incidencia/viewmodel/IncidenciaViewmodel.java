package com.micromax.incidencia.viewmodel;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Tecnico;
import com.micromax.incidencia.dto.IncidenciaDTO;
import lombok.Data;

import java.util.List;

@Data
public class IncidenciaViewmodel {

    private Incidencia incidencia;
    private IncidenciaDTO incidenciaDTO;
    private String message;
    private List<Categoria> categorias;
    private List<Tecnico> tecnicos;
}
