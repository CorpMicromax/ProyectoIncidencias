package com.micromax.incidencia.service.impl;

import com.micromax.incidencia.domain.Constants;
import com.micromax.incidencia.domain.Status;
import com.micromax.incidencia.domain.entities.Historico;
import com.micromax.incidencia.domain.entities.TipoCambio;
import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Tecnico;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.dto.IncidenciaDTO;
import com.micromax.incidencia.repository.CategoriaRepository;
import com.micromax.incidencia.repository.IncidenciaRepository;
import com.micromax.incidencia.service.HistoricoService;
import com.micromax.incidencia.service.IncidenciaService;
import com.micromax.incidencia.service.UsuarioService;
import com.micromax.incidencia.viewmodel.DashboardViewmodel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Service
@Slf4j
public class IncidenciaServiceImpl implements IncidenciaService {

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HistoricoService historia;

    @Autowired
    private EstrategiaServiceImpl estrategiaService;

    @Override
    public List<Incidencia> getIncidencias() {
        log.info("Buscando todas las incidencias");
        return (ArrayList<Incidencia>) incidenciaRepository.findAllByHabilitadoIsTrue();
    }

    @Override
    public void guardarIncidencia(IncidenciaDTO dto, Usuario user){
        Incidencia i = new Incidencia();
        i.setTitulo(dto.getTitulo());
        i.setDescripcion(dto.getDescripcion());
        i.setCategoria(dto.getCategoria());
        i.setCreador(user);
        i.setCreacion(Date.from(LocalDateTime.now().toInstant(ZoneOffset.ofHours(0))));
        i.setStatus(Status.NUEVA);
        i.setAsignados(new ArrayList<>());
        estrategiaService.ejecutarEstrategia(i, usuarioService.getTecnicos());
        i = incidenciaRepository.save(i);
        historia.guardarHistorico(new Historico(i,TipoCambio.CREACION_INCIDENCIA, null, Status.NUEVA,null), user);
        log.info("Usuario %s ha creado una incidencia nueva con id %d", i.getIdIncidencia(), user);
    }

    public void cambiarAsignados(IncidenciaDTO incidenciaDTO){

    }

    @Override
    @Transactional
    public void actualizarIncidencia(IncidenciaDTO dto, Usuario user) {
        Categoria cat = categoriaRepository.findById(dto.getCategoria().getId());
        Optional<Incidencia> i = incidenciaRepository.findByIdIncidenciaAndHabilitadoIsTrue(dto.getId());

        if(i.isPresent()){
            Incidencia in = i.get();

            historia.guardarHistorico(
                    new Historico(in,TipoCambio.EDICION_INCIDENCIA, in.getStatus(), dto.getStatus(),null),
                    user);

            in.setAsignados(defaultIfNull(dto.getAsignados(), new ArrayList<>()));
            in.setCategoria(defaultIfNull(cat, in.getCategoria()));
            in.setTitulo(defaultIfNull(dto.getTitulo(), in.getTitulo()));
            in.setTipoIncidencia(defaultIfNull(dto.getTipoIncidencia(), in.getTipoIncidencia()));
            in.setDescripcion(defaultIfNull(dto.getDescripcion(), in.getDescripcion()));
            in.setStatus(defaultIfNull(dto.getStatus(), in.getStatus()));
            if(in.getStatus().equals(Status.NUEVA) && !in.getAsignados().isEmpty()){
                in.setStatus(Status.ASIGNADA);
            }else if(in.getStatus().equals(Status.ASIGNADA) && in.getAsignados().isEmpty()){
                in.setStatus(Status.ABIERTA);
            }

            in.setTiempoEstimado(defaultIfNull(dto.getTiempoEstimado(), in.getTiempoEstimado()));

            incidenciaRepository.save(in);

        }else{
            log.warn("No se pudo encontrar ninguna incidencia de id= %s", dto.getId());
        }
    }

    @Override
    public Incidencia getIncidenciaById(String id) {
        log.info("Buscando incidencia con id %d", id);
        return incidenciaRepository.findByIdIncidenciaAndHabilitadoIsTrue(id).orElse(null);
    }

    @Override
    public boolean borrarIncidencia(String id, Usuario user) {
        Incidencia i = incidenciaRepository.findByIdIncidenciaAndHabilitadoIsTrue(id).orElse(  null);
        if(i != null) {
            i.setHabilitado(false);
            log.info("Eliminada incidencia con id %d", id);
            historia.guardarHistorico(
                    new Historico(i,TipoCambio.ELIMINACION_INCIDENCIA, i.getStatus(), null,null),
                    user);
            return incidenciaRepository.save(i) != null;
        }

        return false;
    }

    @Override
    public List<Incidencia> obtenerIncidenciasPorCreador(Usuario creador) {
        return incidenciaRepository.findAllByCreadorAndHabilitadoIsTrue(creador);
    }

    @Override
    public List<Incidencia> incidenciasPorEstado(Status status) {
        return incidenciaRepository.findAllByStatusAndHabilitadoIsTrue(status);
    }

    @Override
    public DashboardViewmodel obtenerTodasIncidenciasPorUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public DashboardViewmodel obtenerTodasIncidencias(Usuario usuario) {
        DashboardViewmodel dash = new DashboardViewmodel();
        dash.setAsignadas(incidenciaRepository.countAllByStatusAndHabilitadoIsTrue(Status.ASIGNADA));
        dash.setNuevas(incidenciaRepository.countAllByStatusAndHabilitadoIsTrue(Status.NUEVA));
        dash.setCerradas(incidenciaRepository.countAllByStatusAndHabilitadoIsTrue(Status.CERRADA));
        dash.setProgreso(incidenciaRepository.countAllByStatusAndHabilitadoIsTrue(Status.PROGRESO));
        dash.setReabiertas(incidenciaRepository.countAllByStatusAndHabilitadoIsTrue(Status.REABIERTA));
        dash.setTodas(incidenciaRepository.countAllByHabilitadoIsTrue());

        if(usuario.getRol().getNombre().equalsIgnoreCase(Constants.ADMINROLE)){
            dash.setIncidencias(incidenciaRepository.findAllByStatusIsNotAndHabilitadoIsTrue(Status.CERRADA));
        }else{
            dash.setIncidencias(incidenciaRepository.findAllByStatusIsNotAndCreadorAndHabilitadoIsTrue(Status.CERRADA, usuario));
            if(usuario instanceof Tecnico ) {
                dash.getIncidencias().addAll(incidenciaRepository.findAllByStatusIsNotAndAsignadosContainsAndHabilitadoIsTrue(Status.CERRADA, (Tecnico) usuario));
            }
        }
        dash.setU(usuario);
        dash.setIncidencias(dash.getIncidencias().subList(0, Math.min(9, dash.getIncidencias().size()-1)));
        return dash;
    }


}
