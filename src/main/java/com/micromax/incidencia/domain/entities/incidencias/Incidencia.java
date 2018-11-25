package com.micromax.incidencia.domain.entities.incidencias;

import com.micromax.incidencia.domain.Constants;
import com.micromax.incidencia.domain.Desactivable;
import com.micromax.incidencia.domain.Status;
import com.micromax.incidencia.domain.TiempoEstimado;
import com.micromax.incidencia.domain.entities.Encuesta;
import com.micromax.incidencia.domain.entities.users.Tecnico;
import com.micromax.incidencia.domain.entities.users.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"asignados", "comentarios", "creador"})
@Entity
@Table(name = "incidencia")
public class Incidencia extends Desactivable implements Serializable {

    @Transient
    private static final long serialVersionUID = 8361927790262577413L;

    @Id
    @GenericGenerator(
            name = "secuencia_asignada",
            strategy = "com.micromax.incidencia.domain.GeneradorSecuencias",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "sequence_name", value = "hibernate_sequence"),
                    @org.hibernate.annotations.Parameter(
                            name = "sequence_prefix", value = "INC_"),
            }
    )
    @GeneratedValue(generator = "secuencia_asignada", strategy = GenerationType.IDENTITY)
    @Column(name = "id_incidencia", nullable = false, unique = true, length = 12)
    private String idIncidencia;

    @Column(name = "titulo", length = 160)
    private String titulo;

    @Column(name = "descripcion", length = 4000)
    private String descripcion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion")
    private Date creacion;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_usuario_creador")
    private Usuario creador;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tipo_incidencia")
    private TipoIncidencia tipoIncidencia;

    @Column(name = "tiempo_estimado", length = 12)
    private String tiempoEstimado;

    public TiempoEstimado getTiempoEstimado(){
        return new TiempoEstimado(creacion, tiempoEstimado);
    }

    public void setTiempoEstimado(TiempoEstimado t){
        tiempoEstimado = t.toString();
    }

    public String creacionFormateada(){
        return Constants.formateador.format(creacion);
    }

    public String creacionFormateadaCorta(){return Constants.formateadorCorto.format(creacion);}

    public boolean estaAsignadaA(Usuario u){
        return asignados.contains(u);
    }

    public String tituloCorto(){
        if(titulo.length()<=20) return titulo;
        return titulo.substring(0, 20) + "...";
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "asignaciones",
            joinColumns = @JoinColumn(
                    name = "id_incidencia", referencedColumnName = "id_incidencia"),
            inverseJoinColumns = @JoinColumn(
                    name = "id_usuario_asignado", referencedColumnName = "id_usuario"))
    private Collection<Tecnico> asignados;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "comentario_id")
    private Collection<Comentario> comentarios;

    @OneToOne
    @JoinColumn(name = "id_encuesta")
    private Encuesta encuesta;

    public void addComentario(Comentario c){
        comentarios.add(c);
        c.setIncidencia(this);
    }

    public void removeComentario(Comentario c){
        comentarios.remove(c);
        c.setIncidencia(null);
    }


}
