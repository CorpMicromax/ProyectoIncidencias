package com.micromax.incidencia.service;

import com.micromax.incidencia.domain.Status;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.dto.IncidenciaDTO;
import com.micromax.incidencia.viewmodel.DashboardViewmodel;

import java.util.List;


public interface IncidenciaService {

    List<Incidencia> getIncidencias();

    void crearIncidencia(IncidenciaDTO i, Usuario user);

    void actualizarIncidencia(IncidenciaDTO i, Usuario user);

    Incidencia getIncidenciaById(String id);

    boolean borrarIncidencia(String id, Usuario user);

    List<Incidencia> obtenerIncidenciasPorCreador(Usuario creador);

    List<Incidencia> incidenciasPorEstado(Status status);

    DashboardViewmodel obtenerTodasIncidenciasPorUsuario(Usuario usuario);

    DashboardViewmodel obtenerTodasIncidencias(Usuario usuario);


}

