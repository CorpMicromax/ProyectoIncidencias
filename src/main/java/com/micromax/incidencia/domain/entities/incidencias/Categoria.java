package com.micromax.incidencia.domain.entities.incidencias;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Categoria implements Serializable {

    @Transient
    private static final long serialVersionUID = 19051213456751207L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String nombre;

    private int nivel;

    private boolean activa = true;

    @OneToOne
    @Nullable
    private Categoria padre;
}
