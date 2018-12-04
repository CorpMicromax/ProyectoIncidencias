package com.micromax.incidencia.service.impl;

import com.micromax.incidencia.domain.Constants;
import com.micromax.incidencia.domain.Status;
import com.micromax.incidencia.domain.TiempoEstimado;
import com.micromax.incidencia.domain.Utilidades;
import com.micromax.incidencia.domain.entities.Historico;
import com.micromax.incidencia.domain.entities.TipoCambio;
import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.domain.entities.incidencias.Comentario;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Tecnico;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.dto.IncidenciaDTO;
import com.micromax.incidencia.repository.CategoriaRepository;
import com.micromax.incidencia.repository.IncidenciaRepository;
import com.micromax.incidencia.service.*;
import com.micromax.incidencia.viewmodel.DashboardViewmodel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private MailService mailService;

    @Autowired
    private ComentarioService comentarioService;

    @Override
    public List<Incidencia> getIncidencias() {
        log.info("Buscando todas las incidencias");
        return (ArrayList<Incidencia>) incidenciaRepository.findAllByHabilitadoIsTrue();
    }

    @Override
    public void crearIncidencia(IncidenciaDTO dto, Usuario user){

        Incidencia i = new Incidencia();
        i.setTitulo(dto.getTitulo());
        i.setDescripcion(dto.getDescripcion());
        i.setCategoria(dto.getCategoria());
        i.setCreador(user);
        i.setCreacion(new Date());
        i.setStatus(Status.NUEVA);
        i.setAsignados(new ArrayList<>());
        i.setTiempoEstimado(new TiempoEstimado(0,0,0));
        estrategiaService.ejecutarEstrategia(i, usuarioService.getTecnicos());
        i = incidenciaRepository.save(i);
        historia.guardarHistorico(new Historico(i,TipoCambio.CREACION_INCIDENCIA, null, Status.NUEVA,null), user);
        mailService.sendEmail(user.getEmail(),"Incidencia creada en Micromax", String.format("Ha creado exitosamente la incidencia %s en el sistema de Incidencias de Micromax, con el titulo \"%s\"", i.getIdIncidencia(),i.getTitulo()));
        log.info("Usuario %s ha creado una incidencia nueva con id %d", i.getIdIncidencia(), user);
    }

    @Override
    @Transactional
    public void actualizarIncidencia(IncidenciaDTO dto, Usuario user) {
        Categoria cat = categoriaRepository.findById(dto.getCategoria().getId());
        Optional<Incidencia> i = incidenciaRepository.findByIdIncidenciaAndHabilitadoIsTrue(dto.getId());

        if(i.isPresent()){
            Comentario c = new Comentario();
            c.setContenido(dto.getComentario());
            c = comentarioService.guardarComentario(c,user);

            historia.guardarHistorico(
                    new Historico(i.get(),TipoCambio.EDICION_INCIDENCIA, i.get().getStatus(), dto.getStatus(),c),
                    user);
            i.get().addComentario(c);
            i.get().setAsignados(defaultIfNull(dto.getAsignados(), new ArrayList<>()));
            i.get().setCategoria(defaultIfNull(cat, i.get().getCategoria()));
            i.get().setTitulo(defaultIfNull(dto.getTitulo(), i.get().getTitulo()));
            i.get().setTipoIncidencia(defaultIfNull(dto.getTipoIncidencia(), i.get().getTipoIncidencia()));
            i.get().setDescripcion(defaultIfNull(dto.getDescripcion(), i.get().getDescripcion()));
            i.get().setStatus(defaultIfNull(dto.getStatus(), i.get().getStatus()));
            if(i.get().getStatus().equals(Status.NUEVA) && !i.get().getAsignados().isEmpty()){
                i.get().setStatus(Status.ASIGNADA);
            }else if(i.get().getStatus().equals(Status.ASIGNADA) && i.get().getAsignados().isEmpty()){
                i.get().setStatus(Status.NUEVA);
            }
            if(dto.getStatus().equals(Status.CERRADA)){
                i.get().setCierre(new Date());
            }
            i.get().setTiempoEstimado(defaultIfNull(dto.getTiempoEstimado(), i.get().getTiempoEstimado()));

            incidenciaRepository.save(i.get());

        }else{
            log.warn(String.format("No se pudo encontrar ninguna incidencia de id= %s", dto.getId()));
        }
    }

    @Override
    public Incidencia getIncidenciaById(String id) {
        log.info(String.format("Buscando incidencia con id %s", id));
        return incidenciaRepository.findByIdIncidenciaAndHabilitadoIsTrue(id).orElse(null);
    }


    @Override
    public boolean borrarIncidencia(String id, Usuario user) {
        Incidencia i = incidenciaRepository.findByIdIncidenciaAndHabilitadoIsTrue(id).orElse(  null);
        if(i != null) {
            i.setHabilitado(false);
            log.info(String.format("Eliminada incidencia con id %s", id));
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

        List<Incidencia> todas = incidenciaRepository.findAllByStatusIsNotAndHabilitadoIsTrue(Status.CERRADA);
        List<Incidencia> retrasadas = todas.stream().filter(Utilidades::retrasada).collect(Collectors.toList());
        dash.setRetrasadas(retrasadas.size());
        if(usuario.getRol().getNombre().equalsIgnoreCase(Constants.ADMINROLE)){
            dash.setIncidencias(todas);
        }else{
            dash.setIncidencias(incidenciaRepository.findAllByStatusIsNotAndCreadorAndHabilitadoIsTrue(Status.CERRADA, usuario));
            if(usuario instanceof Tecnico ) {
                dash.getIncidencias().addAll(incidenciaRepository.findAllByStatusIsNotAndAsignadosContainsAndHabilitadoIsTrue(Status.CERRADA, (Tecnico) usuario));
            }
        }
        dash.setU(usuario);
        if (!dash.getIncidencias().isEmpty()) {
            dash.setIncidencias(dash.getIncidencias().subList(0, Math.min(9, dash.getIncidencias().size())));
        }
        return dash;
    }

    @Override
    public void cambioStatus(IncidenciaDTO dto, Usuario user){
        Optional<Incidencia> i = incidenciaRepository.findByIdIncidenciaAndHabilitadoIsTrue(dto.getId());
        if(i.isPresent()){
            historia.guardarHistorico(
                    new Historico(i.get(),TipoCambio.CAMBIO_STATUS, i.get().getStatus(), dto.getStatus(),null),
                    user);
            i.get().setStatus(defaultIfNull(dto.getStatus(), i.get().getStatus()));
            if(i.get().getStatus().equals(Status.NUEVA) && !i.get().getAsignados().isEmpty()){
                i.get().setStatus(Status.ASIGNADA);
            }else if(i.get().getStatus().equals(Status.ASIGNADA) && i.get().getAsignados().isEmpty()){
                i.get().setStatus(Status.NUEVA);
            }
            if(dto.getStatus().equals(Status.CERRADA)){
                i.get().setCierre(new Date());
            }
            incidenciaRepository.save(i.get());
        }else{
            log.warn(String.format("No se pudo encontrar ninguna incidencia de id= %s", dto.getId()));
        }
    }

    @Override
    public void comentar(String comentario, IncidenciaDTO dto, Usuario user){
        Comentario c = comentarioService.guardarComentario(new Comentario(), user);
        c.setContenido(comentario);
        Optional<Incidencia> i = incidenciaRepository.findByIdIncidenciaAndHabilitadoIsTrue(dto.getId());
        if(i.isPresent()){
            i.get().addComentario(c);
            incidenciaRepository.save(i.get());
        }else{
            log.info(String.format("No se pudo encontrar ninguna incidencia de id= %s", dto.getId()));
        }

    }
}
