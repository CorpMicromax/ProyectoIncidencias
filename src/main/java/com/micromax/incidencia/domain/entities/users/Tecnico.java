package com.micromax.incidencia.domain.entities.users;

import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@DiscriminatorValue("T")
@Entity
public class Tecnico extends Usuario implements Serializable {

    @Transient
    private static final long serialVersionUID = 9L;

    @Column(name = "capacidad")
    private int capacidad;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "tecnico_categorias",
            joinColumns = { @JoinColumn(name = "id_usuario") },
            inverseJoinColumns = { @JoinColumn(name = "id_categoria") }
    )
    private List<Categoria> categoriasTecnico;
}
