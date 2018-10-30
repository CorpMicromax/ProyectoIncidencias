package com.micromax.incidencia.service.impl;

import com.micromax.incidencia.domain.Status;
import com.micromax.incidencia.domain.entities.incidencias.Categoria;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.dto.IncidenciaDTO;
import com.micromax.incidencia.repository.CategoriaRepository;
import com.micromax.incidencia.repository.IncidenciaRepository;
import com.micromax.incidencia.service.IncidenciaService;
import com.micromax.incidencia.service.UsuarioService;
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
    private EstrategiaService estrategiaService;

    @Override
    public List<Incidencia> getIncidencias() {
        log.info("Buscando todas las incidencias");
        return (ArrayList<Incidencia>) incidenciaRepository.findAllByHabilitado(true);
    }

    @Override
    public void guardarIncidencia(IncidenciaDTO dto, String username){
        Incidencia i = new Incidencia();
        i.setTitulo(dto.getTitulo());
        i.setDescripcion(dto.getDescripcion());
        i.setCategoria(dto.getCategoria());
        i.setCreador(usuarioService.getUsuarioByUsername(username));
        i.setCreacion(Date.from(LocalDateTime.now().toInstant(ZoneOffset.ofHours(0))));
        i.setStatus(Status.NUEVA);


        usuarioService.asignarTecnico(estrategiaService.ejecutarEstrategia(i, usuarioService.getTecnicos()));

        incidenciaRepository.save(i);
        log.info("Usuario %s ha creado una incidencia nueva con id %d", i.getIdIncidencia(), username);
    }

    public void cambiarAsignados(IncidenciaDTO incidenciaDTO){


    }

    @Override
    @Transactional
    public void actualizarIncidencia(IncidenciaDTO dto) {
        Categoria cat = categoriaRepository.findById(dto.getCategoria().getId());
        Optional<Incidencia> i = incidenciaRepository.findByIdIncidenciaAndHabilitado(dto.getId(), true);
        if(i.isPresent()){
            Incidencia in = i.get();

            in.setCategoria(defaultIfNull(cat, in.getCategoria()));


            in.setTitulo(defaultIfNull(dto.getTitulo(), in.getTitulo()));
            in.setTipoIncidencia(defaultIfNull(dto.getTipoIncidencia(), in.getTipoIncidencia()));
            in.setDescripcion(defaultIfNull(dto.getDescripcion(), in.getDescripcion()));
            in.setStatus(defaultIfNull(dto.getStatus(), in.getStatus()));
            in.setTiempoEstimado(defaultIfNull(dto.getTiempoEstimado(), in.getTiempoEstimado()));
            incidenciaRepository.save(in);
        }else{
            log.warn("No se pudo encontrar ninguna incidencia de id= %s", dto.getId());
        }
    }

    @Override
    public Incidencia getIncidenciaById(String id) {
        log.info("Buscando incidencia con id %d", id);
        return incidenciaRepository.findByIdIncidenciaAndHabilitado(id, true).orElse(null);
    }

    @Override
    public boolean borrarIncidencia(String id) {
        Incidencia i = incidenciaRepository.findByIdIncidenciaAndHabilitado(id, true).orElse(  null);
        if(i != null) {
            i.setHabilitado(false);
            log.info("Eliminada incidencia con id %d", id);
            return incidenciaRepository.save(i) != null;
        }
        return false;
    }

    @Override
    public List<Incidencia> obtenerIncidenciasPorCreador(Usuario creador) {
        return incidenciaRepository.findAllByCreadorAndHabilitado(creador, true);
    }


}
