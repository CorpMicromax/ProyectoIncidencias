package com.micromax.incidencia.domain.entities;


import com.micromax.incidencia.domain.Constants;
import com.micromax.incidencia.domain.Status;
import com.micromax.incidencia.domain.Utilidades;
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

    @Transient
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

    public Historico(Incidencia i, TipoCambio t, Status sa, Status sn, Comentario c){
        this.incidencia = i;
        this.statusAnterior = sa;
        this.statusNuevo = sn;
        this.tipoCambio = t;
        this.comentario = c;
    }

    public String obtenerMensaje(){
        String mensaje;
        String i = incidencia.getIdIncidencia();
        String u = usuarioResponsable != null ? usuarioResponsable.getUsername() : "Admin";
        String sa = statusAnterior != null ? statusAnterior.toString() : "Inexistente";
        String sn = statusNuevo != null ? statusNuevo.toString() : "";
        String m = Constants.formateador.format(momento);
        switch (tipoCambio){
            case CREACION_INCIDENCIA: mensaje = String.format("%s: CREADA. Usuario: %s", m, u);
            break;
            case CAMBIO_STATUS: mensaje = String.format("%s: CAMBIO STATUS, anterior: %s, nuevo: %s. Usuario: %s", m, sa, sn, u);
            break;
            case EDICION_INCIDENCIA: mensaje = String.format("%s: EDICION. Usuario: %s", m, u);
            break;
            case COMENTARIO: mensaje = String.format("%s: COMENTARIO. Comentario: \"%s\" Usuario %s.", Utilidades.acortarString(comentario.getContenido(),25),i,u);
            break;
            default: mensaje = "Ocurrio un error recuperando historico";
        }
        return obtenerFecha() + " " + mensaje;
    }

    public String obtenerFecha() {
       return Constants.formateador.format(this.momento);
    }
}
