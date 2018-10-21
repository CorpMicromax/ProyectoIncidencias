package com.micromax.incidencia.dto;

import com.micromax.incidencia.domain.Status;
import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.domain.entities.users.Usuario;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IncidenciaDTO {

    private String titulo;
    private String descripcion;
    private Status status;
    private Categoria categoria;
    private LocalDateTime creacion;
    private Usuario creador;
}
