package com.micromax.incidencia.service.impl;

import com.micromax.incidencia.domain.Constants;
import com.micromax.incidencia.domain.entities.incidencias.Incidencia;
import com.micromax.incidencia.domain.entities.users.Tecnico;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConfigurationProperties
public class EstrategiaService {



    @Value("${micromax.estragegia}")
    public String tipoEstrategia;

    public void ejecutarEstrategia(Incidencia incidencia, List<Tecnico> tecnicos){

        if(tipoEstrategia.equalsIgnoreCase(Constants.AUTO)){

        }
        if(tipoEstrategia.equalsIgnoreCase(Constants.MANUAL)){
            //No se hace nada :)
        }

    }
}
