package com.micromax.incidencia.service.impl;

import com.micromax.incidencia.config.ConfiguracionGeneral;
import com.micromax.incidencia.domain.Constants;
import com.micromax.incidencia.domain.Status;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Tecnico;
import com.micromax.incidencia.service.EstrategiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstrategiaServiceImpl implements EstrategiaService {

    @Autowired
    private ConfiguracionGeneral config;

    public Tecnico ejecutarEstrategia(Incidencia incidencia, List<Tecnico> tecnicos){

        if(config.getTipoEstrategia().equalsIgnoreCase(Constants.AUTO)){
            List<Tecnico> disponibles = new ArrayList<>();

            for (Tecnico tecnico : tecnicos) {
                if (tecnico.getCategoriasTecnico().contains(incidencia.getCategoria())) {
                    disponibles.add(tecnico);
                }
            }
            Tecnico elegido = encontrarTecnicoMasDisponible(disponibles);
            if(elegido != null) {
                incidencia.getAsignados().add(elegido);
                incidencia.setStatus(Status.ASIGNADA);
                return elegido;
            }

        }
        return null;
    }

    public Tecnico encontrarTecnicoMasDisponible(List<Tecnico> tecs){
        if(tecs.isEmpty()) return null;

        int min = 0;
        int index = 0;
        for (int i = 0; i < tecs.size(); i++){
            int cantidad_asignaciones = tecs.get(i).getAsignaciones().stream().filter(incidencia -> incidencia.isHabilitado()).toArray().length;

            if(i == 0){
                min = cantidad_asignaciones;
                index = 0;
            }else{
                if(cantidad_asignaciones < min) {
                    min = cantidad_asignaciones;
                    index = i;
                }
            }
        }

        return tecs.get(index);
    }
}
