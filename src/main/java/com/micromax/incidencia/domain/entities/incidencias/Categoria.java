package com.micromax.incidencia.domain.entities.incidencias;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.micromax.incidencia.domain.Desactivable;
import com.micromax.incidencia.domain.entities.users.Tecnico;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@ToString(exclude = {"tecnicos", "incidencias"})
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

    @ManyToOne
    @JoinColumn(name = "id_padre", referencedColumnName = "id_categoria")
    private Categoria padre;

    @Column(name = "peso", precision = 3)
    private short peso;

    @Column(name = "tiempo_estimado")
    private String tiempoEstimado;

    @ManyToMany(mappedBy = "categoriasTecnico")
    @JsonIgnore
    private List<Tecnico> tecnicos;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private List<Incidencia> incidencias;

}
