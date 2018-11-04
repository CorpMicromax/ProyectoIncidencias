package com.micromax.incidencia.viewmodel;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.dto.CategoriaDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CategoriaViewmodel {

    private CategoriaDTO categoriaDTO;
    private String message;
    private List<Categoria> categorias;
    private int nivel;

}
