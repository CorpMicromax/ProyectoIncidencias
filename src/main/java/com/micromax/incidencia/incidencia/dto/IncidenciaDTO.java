package com.micromax.incidencia.incidencia.dto;

import com.micromax.incidencia.incidencia.domain.Status;
import com.micromax.incidencia.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.incidencia.domain.entities.users.Usuario;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class IncidenciaDTO {

    public String titulo;
    public String descripcion;
    public Status status;
    public Categoria categoria;
    public LocalDateTime creacion;
    public Usuario creador;
}
