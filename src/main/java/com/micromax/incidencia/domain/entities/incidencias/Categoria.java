package com.micromax.incidencia.domain.entities.incidencias;

import com.micromax.incidencia.domain.Desactivable;
import com.micromax.incidencia.dto.CategoriaDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "categoria")
public class Categoria extends Desactivable implements Serializable {

    @Transient
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria", unique = true, nullable = false, precision = 3)
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "nivel")
    private int nivel;

    @Nullable
    @Column(name = "padre")
    private Categoria padre;

    public Categoria(){}

    public Categoria(CategoriaDTO cat){
        setNombre(cat.getNombre());

        if(cat.getPadre() != null){
            setPadre(cat.getPadre());
            setNivel(cat.getPadre().nivel + 1);
        }else{
            setNivel(0);
        }


    }
}
