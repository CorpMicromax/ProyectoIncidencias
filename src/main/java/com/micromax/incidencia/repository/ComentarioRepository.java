package com.micromax.incidencia.repository;

import com.micromax.incidencia.domain.entities.incidencias.Comentario;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ComentarioRepository extends CrudRepository<Comentario,Long> {

    Optional<Comentario> findComentarioByIdComentarioAndIncidencia (long idComentario, Incidencia incidencia);

    Optional<Comentario> findComentarioByIncidenciaAndAutorAndCreacion (Incidencia incidencia, Usuario usuario, Date FechaCreacion);
    Comentario findComentarioByIdComentarioAndIncidencia(long idComentario, Incidencia incidencia);

    Comentario findComentarioByIncidenciaAndAutorAndCreacion(Incidencia incidencia, Usuario usuario, Date fechaCreacion);

    List<Comentario> findAllByIncidencia(Incidencia incidencia);
}
