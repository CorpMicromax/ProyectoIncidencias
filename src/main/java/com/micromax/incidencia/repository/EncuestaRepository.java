package com.micromax.incidencia.repository;

import com.micromax.incidencia.domain.entities.Encuesta;
import com.micromax.incidencia.domain.entities.users.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EncuestaRepository extends CrudRepository<Encuesta,Long> {

    Encuesta findEncuestaByIdEncuestaAndHabilitadoAndUsuario (long idEncuesta, boolean h, Usuario usuario);

    List<Encuesta> findAllByUsuarioAndHabilitado(Usuario usuario, boolean h);

}
