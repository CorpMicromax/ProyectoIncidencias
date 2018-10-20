package com.micromax.incidencia.dto;

import com.micromax.incidencia.domain.Status;
import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.domain.entities.users.Usuario;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IncidenciaDTO {

    public String titulo;
    public String descripcion;
    public Status status;
    public Categoria categoria;
    public LocalDateTime creacion;
    public Usuario creador;
}
