package com.micromax.incidencia.domain.entities.incidencias;

import com.micromax.incidencia.domain.Desactivable;
import com.micromax.incidencia.dto.CategoriaDTO;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Data
@Entity
public class Categoria extends Desactivable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String nombre;

    private int nivel;

    @OneToOne
    @Nullable
    private Categoria padre;

    public Categoria(CategoriaDTO cat){
        setNombre(cat.getNombre());
        setPadre(cat.getPadre());
        setNivel(cat.getPadre().nivel + 1);
    }
}
