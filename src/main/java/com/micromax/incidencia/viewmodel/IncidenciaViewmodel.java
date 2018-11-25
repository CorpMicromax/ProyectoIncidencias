package com.micromax.incidencia.viewmodel;

import com.micromax.incidencia.domain.Status;
import com.micromax.incidencia.domain.entities.Historico;
import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.incidencias.TipoIncidencia;
import com.micromax.incidencia.domain.entities.users.Tecnico;
import com.micromax.incidencia.dto.IncidenciaDTO;
import lombok.Data;

import java.util.EnumSet;
import java.util.List;

@Data
public class IncidenciaViewmodel {

    private Incidencia incidencia;
    private IncidenciaDTO incidenciaDTO;
    private String message;
    private List<TipoIncidencia> tipoIncidencias;
    private List<Categoria> categorias;
    private List<Tecnico> tecnicos;
    private List<Historico> historico;
    private EnumSet<Status> stati = EnumSet.allOf(Status.class);
    private String comentario;
}
