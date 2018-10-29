package com.micromax.incidencia.repository;

import com.micromax.incidencia.domain.entities.Historico;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface HistoricoRepository extends CrudRepository<Historico,Long> {

    Optional<Historico> findHistoricoByIdHistoricoAndIncidencia (Long idHistorico, String idIncidencia);

    Optional<Historico> findHistoricoByIdHistoricoAndIncidenciaAndUsuarioResponsable (long idHistorico, String idIncidencia, long IdUsuario);

    Collection<Historico> findAllByIncidencia (String idIncidencia);

    Collection<Historico> findAllByIncidenciaAndUsuarioResponsable (String idIncidencia, long IdUuario);



}
