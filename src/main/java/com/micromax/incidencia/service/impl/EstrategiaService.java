package com.micromax.incidencia.service.impl;

import com.micromax.incidencia.domain.Constants;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Tecnico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstrategiaService {

    @Autowired
    private Constants constants;

    public Tecnico ejecutarEstrategia(Incidencia incidencia, List<Tecnico> tecnicos){

        if(constants.getTIPO_ESTRATEGIA().equalsIgnoreCase(Constants.AUTO)){
            List<Tecnico> disponibles = new ArrayList<>();

            for (Tecnico tecnico : tecnicos) {
                if (tecnico.getCategoriasTecnico().contains(incidencia.getCategoria())) {
                    disponibles.add(tecnico);
                }
            }

            Tecnico elegido = encontrarTecnicoMasDisponible(disponibles);
            elegido.getIncidencias().add(incidencia);
            return elegido;
        }
        return null;
    }

    public Tecnico encontrarTecnicoMasDisponible(List<Tecnico> tecs){
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < tecs.size(); i++){
            int size = tecs.get(i).getIncidencias().size();
            if(tecs.get(i).getIncidencias().size()< min) {
                min = size;
                index = i;
            }
        }
        return tecs.get(index);
    }
}
