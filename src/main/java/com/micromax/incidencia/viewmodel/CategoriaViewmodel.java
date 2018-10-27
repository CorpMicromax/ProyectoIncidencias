package com.micromax.incidencia.viewmodel;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import lombok.Data;

import java.util.List;

@Data
public class CategoriaViewmodel {

    private Categoria categoria;
    private String message;
    private List<Categoria> categorias;
    private int nivel;

}
