package com.micromax.incidencia.dto;

import com.micromax.incidencia.domain.Status;
import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.incidencias.TipoIncidencia;
import lombok.Data;

@Data
public class IncidenciaDTO {

    private String id;
    private String titulo;
    private String descripcion;
    private Categoria categoria;
    private Status status;
    private TipoIncidencia tipoIncidencia;
    private String tiempoEstimado;
    private long categoriaId;

    public IncidenciaDTO(){}

    public IncidenciaDTO(Incidencia incidencia){
        id = incidencia.getIdIncidencia();
        titulo = incidencia.getTitulo();
        descripcion = incidencia.getDescripcion();
        categoria = incidencia.getCategoria();
        status = incidencia.getStatus();
        tipoIncidencia = incidencia.getTipoIncidencia();
    }

}
