package com.micromax.incidencia.repository;

import com.micromax.incidencia.domain.entities.Historico;
import com.micromax.incidencia.domain.entities.incidencias.Comentario;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface HistoricoRepository extends CrudRepository<Historico,Long> {

    Optional<Historico> findHistoricoByIdHistoricoAndIncidencia (long idHistorico, Incidencia incidencia);

 //   Optional<Historico> findHistoricoByIdHistoricoAndComentarioAndIncidencia(long idHistorico, Comentario comentario, Incidencia incidencia);

    Optional<Historico> findHistoricoByIdHistoricoAndIncidenciaAndUsuarioResponsable (long idHistorico, Incidencia incidencia, Usuario usuario);

    Collection<Historico> findAllByIncidencia (Incidencia incidencia);

    Collection<Historico> findAllByIncidenciaAndUsuarioResponsable (Incidencia incidencia, Usuario usuario);

}
