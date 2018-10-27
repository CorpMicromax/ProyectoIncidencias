package com.micromax.incidencia.domain.entities;


import com.micromax.incidencia.domain.Status;
import com.micromax.incidencia.domain.entities.incidencias.Comentario;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Usuario;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "historico")
public class Historico implements Serializable {

    private static final long serialVersionUID = 125L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico", unique = true, nullable = false, precision = 12)
    private long idHistorico;

    @ManyToOne
    @JoinColumn(name = "id_incidencia", referencedColumnName = "id_incidencia")
    private Incidencia incidencia;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuarioResponsable;

    @Column(name = "tipo_cambio")
    @Enumerated(EnumType.ORDINAL)
    private TipoCambio tipoCambio;

    @Column(name = "status_anterior")
    @Enumerated(EnumType.ORDINAL)
    private Status statusAnterior;

    @Column(name = "status_nuevo")
    @Enumerated(EnumType.ORDINAL)
    private Status statusNuevo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "momento")
    private Date momento;

    @OneToOne
    @JoinColumn(name = "id_comentario", referencedColumnName = "id_comentario")
    private Comentario comentario;
}
