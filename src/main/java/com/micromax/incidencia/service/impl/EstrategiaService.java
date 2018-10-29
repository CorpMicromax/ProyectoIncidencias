package com.micromax.incidencia.service.impl;

import com.micromax.incidencia.domain.Constants;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Tecnico;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class EstrategiaService {



    
    public void ejecutarEstrategia(Incidencia incidencia, List<Tecnico> tecnicos){

        if(Constants.TIPO_ESTRATEGIA.equalsIgnoreCase(Constants.AUTO)){

        }
        if(Constants.TIPO_ESTRATEGIA.equalsIgnoreCase(Constants.MANUAL)){
            //No se hace nada :)
        }

    }
}
